package org.kelf54.model.statistic;

import org.kelf54.model.common.DataType;
import org.kelf54.model.common.ProcessedDataHandler;

import java.util.HashMap;

public class StatisticManager implements ProcessedDataHandler {
    private final HashMap<DataType, StatisticCollector> mapStatisticCollector = new HashMap<>();
    private final LevelOfStatistics levelOfStatistics;

    public StatisticManager(LevelOfStatistics levelOfStatistics) {
        this.levelOfStatistics = levelOfStatistics;
    }

    @Override
    public void takeProcessedData(DataType dataType, String data) {
        if (!mapStatisticCollector.containsKey(dataType)) {
            switch (dataType) {
                case INTEGER -> mapStatisticCollector.put(DataType.INTEGER, new StatisticForIntegers(levelOfStatistics));
                case REAL -> mapStatisticCollector.put(DataType.REAL, new StatisticForRealNumbers(levelOfStatistics));
                case STRING -> mapStatisticCollector.put(DataType.STRING, new StatisticForStrings(levelOfStatistics));
            }
        }
        mapStatisticCollector.get(dataType).addToStatistic(data);
    }

    public String getStatistic() {
        StringBuilder statistics = new StringBuilder();
        for (StatisticCollector statisticCollector : mapStatisticCollector.values()) {
            statistics.append(statisticCollector.getStatistic()).append("\n");
        }
        return statistics.toString();
    }
}
