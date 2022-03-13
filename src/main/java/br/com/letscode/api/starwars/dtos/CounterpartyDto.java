package br.com.letscode.api.starwars.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class CounterpartyDto {

    @NotNull
    private UUID counterpartyId;
    @NotNull
    private UUID dealId;

}
