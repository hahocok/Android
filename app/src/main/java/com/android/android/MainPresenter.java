package com.android.android;

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
        isCloudinessVisible = true;
        isHumidityVisible = true;
    }

    // Увеличение счетчика

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(String value){
        temperature = value;
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

    public boolean isHumidityVisible() {
        return isHumidityVisible;
    }

    public void setHumidityVisible(boolean humidityVisible) {
        isHumidityVisible = humidityVisible;
    }

    public static MainPresenter getInstance(){
        synchronized (syncObj) {
            if (instance == null) {
                instance = new MainPresenter();
            }
            return instance;
        }
    }
}
