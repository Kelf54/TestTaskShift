package org.kelf54.model.writer;

import java.io.IOException;

public class WriterReal extends WriterData {

    public WriterReal(String patch, boolean flagAppend) throws IOException {
        super(patch, "float", flagAppend);
    }

    @Override
    public void write(String data) throws IOException {
        writer.write(data);
        writer.newLine();
    }

    @Override
    public void close() throws Exception {
        if (writer != null) {
            writer.close();
        }
    }
}
