package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.utils.GenderEnum;
import br.com.letscode.api.starwars.models.Item;
import br.com.letscode.api.starwars.models.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@Getter
public class RebelDto {

    private String name;
    private Integer age;
    private GenderEnum gender;
    private Location location;
    private List<Item> inventory;
}
