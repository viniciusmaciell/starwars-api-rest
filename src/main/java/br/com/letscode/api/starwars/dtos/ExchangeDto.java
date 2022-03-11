package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.models.Item;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ExchangeDto {

    private UUID rebelId;
    private UUID proposedExchangeId;
    private List<Item> offer;

}
