package org.kelf54.model.statistic;

public class StatisticString implements StatisticCollector{
    LevelOfStatistics levelOfStatistics;
    private int countString = 0;
    boolean init = false;
    private Integer lengthMin;
    private Integer lengthMax;
    public StatisticString(LevelOfStatistics levelOfStatistics){
        this.levelOfStatistics = levelOfStatistics;
    }

    @Override
    public void addToStatistic(String data) {
        countString++;
        if(levelOfStatistics == LevelOfStatistics.ADVANCED){
            if(init){
                lengthMin = Math.min(lengthMin, data.length());
                lengthMax = Math.max(lengthMax, data.length());
            }else {
                init = true;
                lengthMin = data.length();
                lengthMax = data.length();
            }
        }
    }

    @Override
    public String getStatistic() {
        String statistic = String.format("""
                Strings
                Quantity -> %d
                """, countString);
        if (levelOfStatistics == LevelOfStatistics.ADVANCED) {
            statistic = statistic + String.format("""
                    length min -> %d
                    length max -> %d
                    """, lengthMin, lengthMax);
        }
        return statistic;
    }
}
