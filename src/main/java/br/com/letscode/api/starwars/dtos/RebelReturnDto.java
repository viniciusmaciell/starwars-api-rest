package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.enums.GenderEnum;
import br.com.letscode.api.starwars.models.Item;
import br.com.letscode.api.starwars.models.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RebelReturnDto {

    private UUID id;
    private String name;
    private Integer age;
    //    @JsonProperty("gender")
    private GenderEnum gender;
    private Location location;
    private Integer confidenceLevel;
    private List<Item> inventory;

}
