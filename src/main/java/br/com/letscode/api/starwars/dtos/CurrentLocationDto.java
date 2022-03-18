package br.com.letscode.api.starwars.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CurrentLocationDto {

    @NotNull
    private Integer latitude;
    @NotNull
    private Integer longitude;
    @NotBlank
    private String baseName;

}
