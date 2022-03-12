package br.com.letscode.api.starwars.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Report {
    private UUID rebelId;
    private UUID traitorId;

}
