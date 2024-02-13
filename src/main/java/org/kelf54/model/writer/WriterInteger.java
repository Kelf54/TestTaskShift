package org.kelf54.model.writer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterInteger extends WriterData{

    public WriterInteger(String patch, boolean flagAppend) throws IOException {
        super(patch, "integer", flagAppend);
    }

    @Override
    public void write(String data) throws IOException {
        writer.write(data);
        writer.newLine();
    }

    @Override
    public void close() throws Exception {
        if (writer != null){
            writer.close();
        }
    }
}
