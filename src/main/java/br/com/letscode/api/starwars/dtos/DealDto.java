package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.models.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@RequiredArgsConstructor
public class DealDto {

    @NotNull
    private UUID partyId;
    @NotNull
    @Valid
    private List<Item> offer;
    @NotNull
    @Valid
    private List<Item> demand;

}
