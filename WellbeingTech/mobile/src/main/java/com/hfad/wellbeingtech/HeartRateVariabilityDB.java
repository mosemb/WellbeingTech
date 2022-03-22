package com.hfad.wellbeingtech;

import java.util.Map;

public class HeartRateVariabilityDB {

    private double heartRateVariability;
    private double meanHeartRate;
    private Map timeStamp;

    public HeartRateVariabilityDB(double heartRateVariability, double meanHeartRate, Map timeStamp) {
        this.heartRateVariability = heartRateVariability;
        this.meanHeartRate = meanHeartRate;
        this.timeStamp = timeStamp;
    }


    public double getHeartRateVariability() {
        return heartRateVariability;
    }

    public void setHeartRateVariability(double heartRateVariability) {
        this.heartRateVariability = heartRateVariability;
    }

    public double getMeanHeartRate() {
        return meanHeartRate;
    }

    public void setMeanHeartRate(double meanHeartRate) {
        this.meanHeartRate = meanHeartRate;
    }

    public Map getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Map timeStamp) {
        this.timeStamp = timeStamp;
    }

}
