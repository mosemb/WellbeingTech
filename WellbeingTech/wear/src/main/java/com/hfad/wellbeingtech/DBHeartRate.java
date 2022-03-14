package com.hfad.wellbeingtech;

import java.util.Map;

public class DBHeartRate {

    private Float heartBeat;
    private Map serverTimestamp;
    // private Date date;

    public DBHeartRate() {
    }


    public DBHeartRate(Float heartBeat, Map serverTimestamp) {
        this.heartBeat = heartBeat;
        this.serverTimestamp = serverTimestamp;
        // this.date = date;
    }



    public Float getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(Float heartBeat) {
        this.heartBeat = heartBeat;
    }

    public Map getServerTimestamp() {
        return serverTimestamp;
    }

    public void setServerTimestamp(Map serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }




}
