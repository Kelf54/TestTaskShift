package org.kelf54.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile {
    List<sourceLineHandler> listHandler = new ArrayList<>();
    public ReaderFile(){}
    public void addHandler (sourceLineHandler handler){
        listHandler.add(handler);
    }
    public void readFile(String patch) throws IOException {
        File fileIn = new File(patch);
        if (!fileIn.exists()) throw new FileNotFoundException();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileIn)))) {
            String line = reader.readLine();
            while (line != null) {
                for(sourceLineHandler handler : listHandler){
                    handler.takeLine(line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
