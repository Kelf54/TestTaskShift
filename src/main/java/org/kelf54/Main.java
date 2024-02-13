package org.kelf54;

import org.kelf54.model.parser.ParserString;
import org.kelf54.model.reader.ReaderFile;
import org.kelf54.model.statistic.LevelOfStatistics;
import org.kelf54.model.statistic.StatisticManager;
import org.kelf54.model.writer.ManagerOutFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        // Запуск без аргументов
        if (args.length == 0) {
            System.out.println("""
                    Usage: [file paths...] [args...]
                                        
                    where the parameters include:
                                        
                    -o          your own path for output files
                                        
                    -p          prefix for the name of the output files
                                        
                    -a          add new data to existing output files
                                        
                    -s | -f     get short or full statistics
                    """);
            return;
        }

        // Обработка аргументов
        List<String> listPatchInFiles = new ArrayList<>();
        String customPatchOutFile = "";
        String filePrefixName = "";
        boolean isAppendToFile = false;
        boolean isReducedStatistics = false;
        boolean isAdvanceStatistics = false;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (Objects.equals(arg, "-o")) {
                i++;
                customPatchOutFile = args[i];
            } else if (Objects.equals(arg, "-p")) {
                i++;
                filePrefixName = args[i];
            } else if (Objects.equals(arg, "-a")) {
                isAppendToFile = true;
            } else if (Objects.equals(arg, "-s")) {
                isReducedStatistics = true;
            } else if (Objects.equals(arg, "-f")) {
                isAdvanceStatistics = true;
            } else {
                listPatchInFiles.add(arg);
            }
        }
        // Если не указали файл
        if (listPatchInFiles.isEmpty()) {
            System.out.println("ERROR | The file path is missing");
            return;
        }

        // Основная инициализация
        ReaderFile readerFile = new ReaderFile();
        ParserString parserString = new ParserString();
        StatisticManager statisticManager = null;

        if(isAdvanceStatistics){
            statisticManager = new StatisticManager(LevelOfStatistics.ADVANCED);
        }else if(isReducedStatistics){
            statisticManager = new StatisticManager(LevelOfStatistics.REDUCED);
        }

        // Создание цепочек связи
        readerFile.addHandler(parserString);
        if (statisticManager != null) {
            parserString.addHandler(statisticManager);
        }

        // Основная работа
        try (ManagerOutFile managerOutFile = new ManagerOutFile(filePrefixName, customPatchOutFile, isAppendToFile)) {
            parserString.addHandler(managerOutFile);
            readerFile.readFile(listPatchInFiles);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Вывод статистики при необходимости
        if (isReducedStatistics || isAdvanceStatistics) {
            System.out.println(statisticManager.getStatistic());
        }
    }
}