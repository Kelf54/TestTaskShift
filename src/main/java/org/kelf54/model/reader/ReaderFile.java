package org.kelf54.model.reader;

import org.kelf54.model.common.SourceLineHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile {
    private final List<SourceLineHandler> listHandler = new ArrayList<>();

    public ReaderFile() {
    }

    public void addHandler(SourceLineHandler handler) {
        listHandler.add(handler);
    }

    public void readFile(List<String> listFilePatches) {
        for (String patch : listFilePatches) {
            try {
                File fileIn = new File(patch);
                if (!fileIn.exists()) throw new FileNotFoundException();
                try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
                    String sourceLine = reader.readLine();
                    while (sourceLine != null) {
                        for (SourceLineHandler handler : listHandler) {
                            handler.takeSourceLine(sourceLine);
                        }
                        sourceLine = reader.readLine();
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.printf("File %s not exist%n", patch);
            } catch (IOException e) {
                System.out.printf("Unexpected error reading file %s%n", patch);
            }
        }
    }
}
