package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.enums.GenderEnum;
import br.com.letscode.api.starwars.models.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RebelReturnDto {
    private Long id;
    private String name;
    private Integer age;
    //    @JsonProperty("gender")
    private GenderEnum gender;
    private Location location;
}
