package com.android.android.cards;

public class Card {
    private String day;
    private String temperature;

    public Card(String day, String temperature){
        this.day = day;
        this.temperature = temperature;
    }

    public String getDay() {
        return day;
    }

    public String getTemperature() {
        return temperature;
    }
}
