package br.com.letscode.api.starwars.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
//@NoArgsConstructor
public class ReportDto {

    private UUID rebelId;
    private UUID traitorId;
}
