package org.kelf54.model.parser;

import org.kelf54.model.common.DataType;
import org.kelf54.model.common.ProcessedDataHandler;
import org.kelf54.model.common.SourceLineHandler;

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

    public void parseData(String line) {
        DataType dataType;
        if (line.matches("[+-]?\\d+")) { // целые числа
            dataType = DataType.INTEGER;
        } else if (line.matches("([+-]?\\d+[.,]\\d+)(E-\\d+)?")) { // вещественные числа
            dataType = DataType.REAL;
        } else { // строка
            dataType = DataType.STRING;
        }
        for (ProcessedDataHandler handler : listHandler) {
            handler.takeProcessedData(dataType, line);
        }
    }
}
