package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.utils.ItemEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReturnDealDto {
    private UUID dealId;
    private UUID partyId;
    private List<ItemEnum> offer;
    private List<ItemEnum> demand;
}
