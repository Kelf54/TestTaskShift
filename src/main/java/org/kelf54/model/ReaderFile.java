package org.kelf54.model;

import java.io.*;

public class ReaderFile {
    public ReaderFile(){}
    public void readFile(String patch) throws IOException {
        File fileIn = new File(patch);
        if (!fileIn.exists()) throw new FileNotFoundException();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileIn)))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.matches("\\d+")) { // целые числа
                    //listInteger.add(Integer.parseInt(line));
                } else if (line.matches("[+-]*\\d+[.,]\\d+")) { // вещественные числа
                    //listFloat.add(Float.parseFloat(line));
                } else { // строка
                    //listString.add(line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
