package br.com.letscode.api.starwars.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class LocationDto {
    @NotNull
    private Integer latitude;
    @NotNull
    private Integer longitude;
    @NotBlank
    private String baseName;

}
