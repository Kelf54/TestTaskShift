package org.kelf54.model.writer;

import java.io.IOException;

public class WriterString extends WriterData {
    public WriterString(String patch, boolean flagAppend) throws IOException {
        super(patch, "string", flagAppend);
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
