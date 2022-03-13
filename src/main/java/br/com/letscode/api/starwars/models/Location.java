package br.com.letscode.api.starwars.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Location {
//    @NotNull
    private Integer latitude;
//    @NotNull
    private Integer longitude;
//    @NotBlank
    private String baseName;

    public Location() {
    }

}
