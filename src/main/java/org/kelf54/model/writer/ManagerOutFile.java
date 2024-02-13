package org.kelf54.model.writer;

import org.kelf54.model.DataType;
import org.kelf54.model.ProcessedDataHandler;

import java.io.IOException;
import java.util.HashMap;

public class ManagerOutFile implements ProcessedDataHandler, AutoCloseable {
    private final HashMap<DataType, WriterData> mapWriterOutFile = new HashMap<>();
    private final boolean flagAppend;
    private final String patch;

    public ManagerOutFile(String prefix, String patch, boolean flagAppend) {
        this.flagAppend = flagAppend;
        this.patch = prefix + patch;
    }

    @Override
    public void takeProcessedData(DataType dataType, String data) {
        try {
            if (!mapWriterOutFile.containsKey(dataType)) {
                switch (dataType) {
                    case INTEGER -> mapWriterOutFile.put(DataType.INTEGER, new WriterInteger(patch, flagAppend));
                    case REAL -> mapWriterOutFile.put(DataType.REAL, new WriterReal(patch, flagAppend));
                    case STRING -> mapWriterOutFile.put(DataType.STRING, new WriterString(patch, flagAppend));
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
