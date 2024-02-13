package org.kelf54.model.statistic;

public class StatisticForRealNumbers extends StatisticForNumbers<Double> {
    public StatisticForRealNumbers(LevelOfStatistics levelOfStatistics) {
        super(levelOfStatistics);
    }

    @Override
    public void addToStatistic(String dataIn) {
        double data = Double.parseDouble(dataIn);
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
                Real numbers
                Quantity -> %d
                """, countOfNumbers);
        if (countOfNumbers > 0 && levelOfStatistics == LevelOfStatistics.ADVANCED) {
            statistic = statistic + String.format("""
                    min -> %f
                    max -> %f
                    avg -> %f
                    sum -> %f
                    """, min, max, avg, sum);
        }
        return statistic;
    }
}
