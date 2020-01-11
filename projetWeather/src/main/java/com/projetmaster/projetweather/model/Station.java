package com.projetmaster.projetweather.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import javax.validation.constraints.NotNull;
import java.util.Objects;


@Document(indexName = "stations")
public class Station {
    @Id
    private String id;
    @GeoPointField
    private GeoPoint position;
    @NotNull
    private String nomStation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeoPoint getPosition() {
        return position;
    }

    public void setPosition(GeoPoint position) {
        this.position = position;
    }

    public String getNomStation() {
        return nomStation;
    }

    public void setNomStation(String nomStation) {
        this.nomStation = nomStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (!id.equals(station.id)) return false;
        if (!position.equals(station.position)) return false;
        return nomStation.equals(station.nomStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, nomStation);
    }

    @Override
    public String toString() {
        return "Station{" +
                "id='" + id + '\'' +
                ", position=" + position +
                ", nomStation='" + nomStation + '\'' +
                '}';
    }
}
