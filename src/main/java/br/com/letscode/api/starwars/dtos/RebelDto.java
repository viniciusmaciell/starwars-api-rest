package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.enums.GenderEnum;
import br.com.letscode.api.starwars.models.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class RebelDto {

    private String name;
    private Integer age;
    private GenderEnum gender;
    private Location location;

}
