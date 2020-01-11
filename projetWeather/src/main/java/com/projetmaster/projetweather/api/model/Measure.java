package com.projetmaster.projetweather.api.model;

import javax.validation.constraints.NotNull;

public class Measure {

    @NotNull
    private float humidity;
    @NotNull
    private float temperature;
    @NotNull
    private float atmPressure;

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getAtmPressure() {
        return atmPressure;
    }

    public void setAtmPressure(float atmPressure) {
        this.atmPressure = atmPressure;
    }
}
