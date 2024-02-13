package org.kelf54.model.statistic;

abstract public class StatisticForNumbers<T extends Number> implements StatisticCollector {
    protected LevelOfStatistics levelOfStatistics;
    protected long countOfNumbers = 0;
    protected T min;
    protected T max;
    protected T avg;
    protected T sum;

    public StatisticForNumbers(LevelOfStatistics levelOfStatistics) {
        this.levelOfStatistics = levelOfStatistics;
    }

    @Override
    abstract public void addToStatistic(String data);

    @Override
    abstract public String getStatistic();
}
