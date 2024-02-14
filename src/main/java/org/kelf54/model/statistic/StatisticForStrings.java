package org.kelf54.model.statistic;

public class StatisticForStrings implements StatisticCollector {
    private final LevelOfStatistics levelOfStatistics;
    private long countOfStrings = 0;
    private long lengthMinString;
    private long lengthMaxString;

    public StatisticForStrings(LevelOfStatistics levelOfStatistics) {
        this.levelOfStatistics = levelOfStatistics;
    }

    @Override
    public void addToStatistic(String data) {
        countOfStrings++;
        if (levelOfStatistics == LevelOfStatistics.ADVANCED) {

            if (countOfStrings > 1) {
                lengthMinString = Math.min(lengthMinString, data.length());
                lengthMaxString = Math.max(lengthMaxString, data.length());
            } else {
                lengthMinString = data.length();
                lengthMaxString = lengthMinString;
            }
        }
    }

    @Override
    public String getStatistic() {
        String statistic = String.format("""
                Strings
                Quantity -> %d
                """, countOfStrings);
        if (countOfStrings > 0 && levelOfStatistics == LevelOfStatistics.ADVANCED) {
            statistic = statistic + String.format("""
                    length min -> %d
                    length max -> %d
                    """, lengthMinString, lengthMaxString);
        }
        return statistic;
    }
}
