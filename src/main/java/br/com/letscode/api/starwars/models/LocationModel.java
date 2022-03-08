package br.com.letscode.api.starwars.models;

import lombok.Data;

@Data
public class LocationModel {
    private Integer latitude;
    private Integer longitude;
    private String baseName;


    public LocationModel(Integer latitude, Integer longitude, String baseName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.baseName = baseName;
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", baseName='" + baseName + '\'' +
                '}';
    }
}
