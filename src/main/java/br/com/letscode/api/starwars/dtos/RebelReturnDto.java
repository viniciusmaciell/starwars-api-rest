package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.enums.GenderEnum;
<<<<<<< HEAD
import br.com.letscode.api.starwars.models.Location;
=======
import br.com.letscode.api.starwars.models.LocationModel;
>>>>>>> c7ed8ee (Muda ReturnedRebelDto para RebelReturnDto)
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RebelReturnDto {
<<<<<<< HEAD
    private Long id;
    private String name;
    private Integer age;
    //    @JsonProperty("gender")
    private GenderEnum gender;
    private Location location;
=======

    private Long id;
    private String  name;
    private Integer age;
    private GenderEnum gender;
    private LocationModel location;

>>>>>>> c7ed8ee (Muda ReturnedRebelDto para RebelReturnDto)
}
