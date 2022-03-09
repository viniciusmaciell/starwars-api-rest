package br.com.letscode.api.starwars.models;

import lombok.Data;

@Data
public class Location {
    private Integer latitude;
    private Integer longitude;
    private String baseName;

    public Location(){
    }

}
