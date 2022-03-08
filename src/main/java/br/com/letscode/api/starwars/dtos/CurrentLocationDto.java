package br.com.letscode.api.starwars.dtos;

import lombok.Data;

@Data
public class CurrentLocationDto {

    private Integer latitude;
    private Integer longitude;
    private String baseName;

}
