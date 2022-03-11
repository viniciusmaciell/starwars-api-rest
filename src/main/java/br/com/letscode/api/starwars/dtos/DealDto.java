package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.models.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@RequiredArgsConstructor
public class DealDto {

    private UUID partyId;
    private List<Item> offer;
    private List<Item> demand;

}
