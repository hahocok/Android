package com.android.android;

import android.view.View;

import java.util.Random;

public final class MainPresenter {
    private static MainPresenter instance = null;
    private static final Object syncObj = new Object();

    // Это наш счетчик
    private String city;
    private String temperature;
    private boolean isCloudinessVisible;
    private boolean isHumidityVisible;

    // Конструктор (вызывать извне его нельзя, поэтому он приватный)
    private MainPresenter(){
        temperature = "0";
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(String value){
        temperature = value;
    }

    public void setRandomTemperature(){
        temperature = getRandomTemp();
    }

    public String getTemperature(){
        return temperature;
    }

    public boolean isCloudinessVisible() {
        return isCloudinessVisible;
    }

    public void setCloudinessVisible(boolean cloudinessVisible) {
        isCloudinessVisible = cloudinessVisible;
    }

    public void setCloudinessVisible(View view) {
        isCloudinessVisible = isVisible(view);
    }

    public boolean isHumidityVisible() {
        return isHumidityVisible;
    }

    public void setHumidityVisible(boolean humidityVisible) {
        isHumidityVisible = humidityVisible;
    }

    public void setHumidityVisible(View view) {
        isHumidityVisible = isVisible(view);
    }

    public static MainPresenter getInstance(){
        synchronized (syncObj) {
            if (instance == null) {
                instance = new MainPresenter();
            }
            return instance;
        }
    }

    private boolean isVisible(View view) {
        int isVisible = view.getVisibility();
        if (isVisible == View.VISIBLE) {
            return true;
        } else {
            return false;
        }
    }

    private String getRandomTemp() {
        int min = -30;
        int max = 30;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return String.valueOf(i);
    }
}
