package org.kelf54.model.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

abstract public class WriterData implements AutoCloseable {
    protected boolean flagAppend;
    protected String patch;
    protected File fileOut;
    protected BufferedWriter writer;

    public WriterData(String patch, String nameType, boolean flagAppend) throws IOException {
        this.patch = patch;
        this.flagAppend = flagAppend;
        fileOut = new File(patch + nameType + ".txt");
        try {
            if (!fileOut.exists()) {
                fileOut.createNewFile();
            }
        } catch (IOException e) {
            throw new IOException("Failed to create a file " + fileOut.getAbsolutePath() + "\n" + e.getMessage());
        }

        try {
            if (flagAppend) {
                writer = new BufferedWriter(new FileWriter(fileOut, true));
            } else {
                writer = new BufferedWriter(new FileWriter(fileOut));
            }
        } catch (IOException e) {
            throw new IOException("The file " + fileOut.getAbsolutePath() + " could not be opened \n" + e.getMessage());
        }

    }

    abstract public void write(String data) throws IOException;
}
