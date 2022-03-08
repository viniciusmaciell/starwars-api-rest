package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.enums.GenderEnum;
import br.com.letscode.api.starwars.models.LocationModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnedRebelDto {

    private Long id;
    private String  name;
    private Integer age;
    private GenderEnum gender;
    private LocationModel location;

}
