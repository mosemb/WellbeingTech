package com.hfad.wellbeingtech;

public class HrvCardData {

    private String HeartRate;
    private Integer image;

    public String getHeartRate() {
        return HeartRate;
    }

    public void setHeartRate(String heartRate) {
        HeartRate = heartRate;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }


    public HrvCardData(String heartRate, Integer image) {
        HeartRate = heartRate;
        this.image = image;
    }


}
