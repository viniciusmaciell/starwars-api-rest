package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.models.Location;
import br.com.letscode.api.starwars.utils.GenderEnum;
import br.com.letscode.api.starwars.utils.ItemEnum;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@RequiredArgsConstructor
@Getter
@Setter
public class RebelDto {
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    @NotNull
    private GenderEnum gender;
    @NotNull
    @Valid
    private Location location;
    @NotNull
    @Valid
    private List<ItemEnum> inventory;
}
