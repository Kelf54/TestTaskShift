package org.kelf54;

import org.kelf54.model.ParserString;
import org.kelf54.model.ReaderFile;
import org.kelf54.model.statistic.LevelOfStatistics;
import org.kelf54.model.statistic.StatisticManager;
import org.kelf54.model.writer.ManagerOutFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        // Запуск без аргументов
        if (args.length == 0){
            System.out.println("Инструкция");
            return;
        }

        // Обработка аргументов
        List<String> listPatchIn = new ArrayList<>();
        String patchOut = "";
        String prefix = "";
        boolean flagA = false;
        boolean flagS = false;
        boolean flagF = false;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (Objects.equals(arg, "-o")) {
                i++;
                patchOut = args[i];
            } else if (Objects.equals(arg, "-p")) {
                i++;
                prefix = args[i];
            } else if (Objects.equals(arg, "-a")) {
                flagA = true;
            } else if (Objects.equals(arg, "-s")) {
                flagS = true;
            } else if (Objects.equals(arg, "-f")) {
                flagF = true;
            } else {
                listPatchIn.add(arg);
            }
        }
        // Если не указали файл
        if (listPatchIn.isEmpty()){
            System.out.println("ERROR | The file path is missing");
            return;
        }

        // Основная инициализация
        ReaderFile readerFile = new ReaderFile();
        ParserString parserString = new ParserString();
        StatisticManager statisticManager = null;
        if (flagS){
            statisticManager = new StatisticManager(LevelOfStatistics.REDUCED);
        }else if (flagF){
            statisticManager = new StatisticManager(LevelOfStatistics.ADVANCED);
        }

        // Создание цепочек связи
        readerFile.addHandler(parserString);
        if (statisticManager != null){
            parserString.addHandler(statisticManager);
        }

        // Основная работа
        try(ManagerOutFile managerOutFile = new ManagerOutFile(prefix, patchOut, flagA)){
            parserString.addHandler(managerOutFile);
            readerFile.readFile(listPatchIn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Вывод статистики при необходимости
        if (flagS || flagF){
            System.out.println(statisticManager.getStatistic());
        }
    }
}