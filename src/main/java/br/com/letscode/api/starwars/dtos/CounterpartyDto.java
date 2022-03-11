package br.com.letscode.api.starwars.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class CounterpartyDto {

    private UUID counterpartyId;
    private UUID dealId;

}
