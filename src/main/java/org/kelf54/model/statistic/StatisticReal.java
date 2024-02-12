package org.kelf54.model.statistic;

public class StatisticReal extends StatisticNumbers<Double>{
    public StatisticReal(LevelOfStatistics levelOfStatistics) {
        super(levelOfStatistics);
    }

    @Override
    public void addToStatistic(String dataIn) {
        double data = Double.parseDouble(dataIn);
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
                Real numbers
                Quantity -> %d
                """, count);
        if (levelOfStatistics == LevelOfStatistics.ADVANCED) {
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
