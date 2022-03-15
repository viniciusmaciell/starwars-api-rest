package br.com.letscode.api.starwars.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemsAmountDto {
    Float weapon;
    Float ammunition;
    Float water;
    Float food;

}
