package org.kelf54.model.writer;

import org.kelf54.model.common.DataType;
import org.kelf54.model.common.ProcessedDataHandler;

import java.io.IOException;
import java.util.HashMap;

public class ManagerOutFile implements ProcessedDataHandler, AutoCloseable {
    private final HashMap<DataType, WriterData> mapWriterOutFile = new HashMap<>();
    private final boolean isAppendToFile;
    private final String filePatch;

    public ManagerOutFile(String filePrefixName, String filePatch, boolean isAppendToFile) {
        this.isAppendToFile = isAppendToFile;
        this.filePatch = filePatch + filePrefixName;
    }

    @Override
    public void takeProcessedData(DataType dataType, String data) {
        try {
            if (!mapWriterOutFile.containsKey(dataType)) {
                switch (dataType) {
                    case INTEGER -> mapWriterOutFile.put(DataType.INTEGER, new WriterInteger(filePatch, isAppendToFile));
                    case REAL -> mapWriterOutFile.put(DataType.REAL, new WriterReal(filePatch, isAppendToFile));
                    case STRING -> mapWriterOutFile.put(DataType.STRING, new WriterString(filePatch, isAppendToFile));
                }
            }
            mapWriterOutFile.get(dataType).write(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        for (WriterData writerData : mapWriterOutFile.values()) {
            writerData.close();
        }
    }
}
