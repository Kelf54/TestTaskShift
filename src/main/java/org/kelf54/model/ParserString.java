package org.kelf54.model;

import java.util.ArrayList;
import java.util.List;

public class ParserString implements SourceLineHandler {
    List<ProcessedDataHandler> listHandler = new ArrayList<>();
    public void addHandler(ProcessedDataHandler handler) {
        listHandler.add(handler);
    }
    @Override
    public void takeSourceLine(String line) {
        parseData(line);
    }
    public void parseData(String line){
        DataType dataType;
        Object data;
        if (line.matches("\\d+")) { // целые числа
            dataType = DataType.INTEGER;
            data = Integer.parseInt(line);
        } else if (line.matches("([+-]*\\d+[.,]\\d+)(E-\\d+)?")) { // вещественные числа
            dataType = DataType.REAL;
            data = Double.parseDouble(line);
        } else { // строка
            dataType = DataType.STRING;
            data = line;
        }
        for (ProcessedDataHandler handler : listHandler){
            handler.takeProcessedData(dataType,data);
        }
    }
}
