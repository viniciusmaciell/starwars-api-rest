package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.enums.ItemEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Item {

    private UUID id;
    private ItemEnum name;
    private Integer score;

    public Item(UUID id, ItemEnum name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
