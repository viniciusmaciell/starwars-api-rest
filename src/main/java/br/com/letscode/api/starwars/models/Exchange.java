package br.com.letscode.api.starwars.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Exchange {

    private Long id;
    private List<Item> offer;
    private List<Item> demand;

    public Exchange(long l, List<Item> asList, List<Item> asList1) {
    }
}
