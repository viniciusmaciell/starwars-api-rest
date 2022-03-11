package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.models.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReturnDealDto {
    private UUID id;
    private UUID partyId;
    private List<Item> offer;
    private List<Item> demand;
}
