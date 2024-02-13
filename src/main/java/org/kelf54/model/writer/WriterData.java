package org.kelf54.model.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

abstract public class WriterData implements AutoCloseable {
    protected File fileOut;
    protected BufferedWriter writer;

    public WriterData(String patch, String nameType, boolean isAppendToFile) throws IOException {
        fileOut = new File(patch + nameType + ".txt");
        try {
            if (!fileOut.exists()) {
                fileOut.createNewFile();
            }
        } catch (IOException e) {
            throw new IOException("Failed to create a file " + fileOut.getAbsolutePath() + "\n" + e.getMessage());
        }

        try {
            writer = new BufferedWriter(new FileWriter(fileOut, isAppendToFile));
        } catch (IOException e) {
            throw new IOException("The file " + fileOut.getAbsolutePath() + " could not be opened \n" + e.getMessage());
        }

    }

    public void write(String data) throws IOException {
        writer.write(data);
        writer.newLine();
    }

    @Override
    public void close() throws Exception {
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }
}
