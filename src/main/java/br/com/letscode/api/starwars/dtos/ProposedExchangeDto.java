package br.com.letscode.api.starwars.dtos;

import br.com.letscode.api.starwars.models.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class ProposedExchangeDto {

    private List<Item> offer;
    private List<Item> demand;

}
