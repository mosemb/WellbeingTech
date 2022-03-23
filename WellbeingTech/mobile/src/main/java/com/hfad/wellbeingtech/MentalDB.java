package com.hfad.wellbeingtech;


import java.util.Map;

public class MentalDB {

    // Model to store stress levels, competence and difficulty faced by an individual

    private Integer stressLevel;
    private Integer incomptenceLevel;
    private Integer difficultryLevel;
    private Map serverTimestamp;

    public MentalDB(Integer stressLevel, Integer incomptenceLevel, Integer difficultryLevel, Map serverTimestamp) {
        this.stressLevel = stressLevel;
        this.incomptenceLevel = incomptenceLevel;
        this.difficultryLevel = difficultryLevel;
        this.serverTimestamp = serverTimestamp;
    }

    public Integer getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(Integer stressLevel) {
        this.stressLevel = stressLevel;
    }

    public Integer getIncomptenceLevel() {
        return incomptenceLevel;
    }

    public void setIncomptenceLevel(Integer incomptenceLevel) {
        this.incomptenceLevel = incomptenceLevel;
    }

    public Integer getDifficultryLevel() {
        return difficultryLevel;
    }

    public void setDifficultryLevel(Integer difficultryLevel) {
        this.difficultryLevel = difficultryLevel;
    }

    public Map getServerTimestamp() {
        return serverTimestamp;
    }

    public void setServerTimestamp(Map serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }


}

