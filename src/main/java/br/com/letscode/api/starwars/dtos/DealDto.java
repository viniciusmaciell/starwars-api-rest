package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.utils.ItemEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class DealDto {

    @NotNull
    private UUID partyId;
    @NotNull
    @Valid
    private List<ItemEnum> offer;
    @NotNull
    @Valid
    private List<ItemEnum> demand;

}
