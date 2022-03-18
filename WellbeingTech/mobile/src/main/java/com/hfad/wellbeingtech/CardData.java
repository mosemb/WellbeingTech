package com.hfad.wellbeingtech;


public class CardData {

    private String mentalState;
    private Integer imageMentalState;

    public CardData(String mentalState, Integer imageMentalState) {
        this.mentalState = mentalState;
        this.imageMentalState = imageMentalState;
    }

    public String getMentalState() {
        return mentalState;
    }

    public void setMentalState(String mentalState) {
        this.mentalState = mentalState;
    }

    public Integer getImageMentalState() {
        return imageMentalState;
    }

    public void setImageMentalState(Integer imageMentalState) {
        this.imageMentalState = imageMentalState;
    }



}

