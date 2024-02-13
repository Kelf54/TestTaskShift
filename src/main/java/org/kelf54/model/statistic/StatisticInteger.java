package org.kelf54.model.statistic;

public class StatisticInteger extends StatisticNumbers<Long>{
    public StatisticInteger(LevelOfStatistics levelOfStatistics) {
        super(levelOfStatistics);
    }

    @Override
    public void addToStatistic(String dataIn) {
        long data = Long.parseLong(dataIn);
        count++;
        if(levelOfStatistics == LevelOfStatistics.ADVANCED){
            if(init){
                min = Math.min(min,data);
                max = Math.max(max,data);
                sum += data;
                avg = sum/count;
            }else {
                init = true;
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
                """, count);
        if (levelOfStatistics == LevelOfStatistics.ADVANCED) {
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
