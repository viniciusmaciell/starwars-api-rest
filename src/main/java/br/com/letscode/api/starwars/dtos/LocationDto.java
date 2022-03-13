package br.com.letscode.api.starwars.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@RequiredArgsConstructor
@Getter
public class LocationDto {
    @NotNull
    private Integer latitude;
    @NotNull
    private Integer longitude;
    @NotBlank
    private String baseName;

}
