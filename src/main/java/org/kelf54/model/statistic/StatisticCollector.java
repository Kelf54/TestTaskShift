package org.kelf54.model.statistic;

public interface StatisticCollector {
    void addToStatistic(String data);
    String getStatistic();
}
