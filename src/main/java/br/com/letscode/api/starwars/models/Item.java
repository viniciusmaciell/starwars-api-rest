package br.com.letscode.api.starwars.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Item {

    private Long id;
    private String name;
    private Integer score;

    public Item(Long id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
