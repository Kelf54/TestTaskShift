package org.kelf54.model.statistic;

abstract public class StatisticNumbers<T extends Number> implements StatisticCollector{
    protected LevelOfStatistics levelOfStatistics;
    protected int count = 0;
    protected boolean init = false;
    protected T min;
    protected T max;
    protected T avg;
    protected T sum;
    public StatisticNumbers (LevelOfStatistics levelOfStatistics){
        this.levelOfStatistics = levelOfStatistics;
    }
    @Override
    abstract public void addToStatistic(String data);
    @Override
    abstract public String getStatistic();
}
