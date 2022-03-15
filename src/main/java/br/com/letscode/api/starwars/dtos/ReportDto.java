package br.com.letscode.api.starwars.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@ToString
public class ReportDto {
    @NotNull
    private UUID rebelId;
    @NotNull
    private UUID traitorId;
}
