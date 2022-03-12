package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.models.Item;
import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.utils.GenderEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RebelReturnDto {

    private UUID id;
    private String name;
    private Integer age;

    private GenderEnum gender;
    private Location location;
//    @JsonProperty("confidence_level")
    private Integer confidenceLevel;
//    @JsonProperty("registration_date")
    private LocalDate registrationDate;
    private List<Item> inventory;

}
