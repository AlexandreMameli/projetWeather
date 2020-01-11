package com.projetmaster.projetweather.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Objects;

@Document(indexName = "measured_data")
public class StationMeasuredData {

    @Id
    private String idMeasure;
    @NotNull
    private String stationId;
    @NotNull
    @PastOrPresent
    @Field(type = FieldType.Date)
    private  Date measureDate;
    @NotNull
    private float humidity;
    @NotNull
    private float temperature;
    @NotNull
    private float atmPressure;

    public StationMeasuredData(@NotNull String stationId, @NotNull @PastOrPresent Date measureDate, @NotNull float humidity, @NotNull float temperature, @NotNull float atmPressure) {
        this.stationId = stationId;
        this.measureDate = measureDate;
        this.humidity = humidity;
        this.temperature = temperature;
        this.atmPressure = atmPressure;
    }

    public String getIdMeasure() {
        return idMeasure;
    }

    public void setIdMeasure(String idMeasure) {
        this.idMeasure = idMeasure;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

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

    public void setAtmPressure(int atmPressure) {
        this.atmPressure = atmPressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationMeasuredData that = (StationMeasuredData) o;

        if (Float.compare(that.humidity, humidity) != 0) return false;
        if (Float.compare(that.temperature, temperature) != 0) return false;
        if (atmPressure != that.atmPressure) return false;
        if (!idMeasure.equals(that.idMeasure)) return false;
        if (!stationId.equals(that.stationId)) return false;
        return measureDate.equals(that.measureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMeasure, stationId, measureDate, humidity, temperature, atmPressure);
    }
}
