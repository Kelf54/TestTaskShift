package org.kelf54.model.statistic;

public class StatisticForIntegers extends StatisticForNumbers<Long> {
    public StatisticForIntegers(LevelOfStatistics levelOfStatistics) {
        super(levelOfStatistics);
    }

    @Override
    public void addToStatistic(String dataIn) {
        long data = Long.parseLong(dataIn);
        countOfNumbers++;
        if (levelOfStatistics == LevelOfStatistics.ADVANCED) {
            if (countOfNumbers > 1) {
                min = Math.min(min, data);
                max = Math.max(max, data);
                sum += data;
                avg = sum / countOfNumbers;
            } else {
                min = data;
                max = data;
                sum = data;
                avg = data;
            }
        }
    }

    @Override
    public String getStatistic() {
        String statistic = String.format("""
                Integer numbers
                Quantity -> %d
                """, countOfNumbers);
        if (countOfNumbers > 0 && levelOfStatistics == LevelOfStatistics.ADVANCED) {
            statistic = statistic + String.format("""
                    min -> %d
                    max -> %d
                    avg -> %d
                    sum -> %d
                    """, min, max, avg, sum);
        }
        return statistic;
    }
}
