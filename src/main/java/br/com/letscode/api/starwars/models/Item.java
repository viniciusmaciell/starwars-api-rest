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

    public Item(long l, String name, Integer score) {
    }
}
