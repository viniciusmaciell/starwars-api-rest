package br.com.letscode.api.starwars.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Exchange {

    private Long id;
    private List<Item> offer;
    private List<Item> demand;

}
