package br.com.letscode.api.starwars.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class ReportDto {
    @NotNull
    private UUID rebelId;
    @NotNull
    private UUID traitorId;
}
