package br.com.letscode.api.starwars.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LostPointsDto {
    Integer weapon;
    Integer ammunition;
    Integer water;
    Integer food;
    @JsonProperty("total_points_lost")
    Integer total;

}
