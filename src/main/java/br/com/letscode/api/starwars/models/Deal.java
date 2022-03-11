package br.com.letscode.api.starwars.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Deal {

    private UUID id;
    private UUID partyId;
    private List<Item> offer;
    private List<Item> demand;

}
