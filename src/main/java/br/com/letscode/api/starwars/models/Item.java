package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.utils.ItemEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class Item {

    private UUID id;
    private ItemEnum name;
    private Integer score;

}
