package br.com.letscode.api.starwars.models;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@RequiredArgsConstructor
public class Exchange {

    private Long id;
    private List<Item> offer;
    private List<Item> demand;

    public Exchange(Long id, List<Item> offer, List<Item> demand) {
        this.id = id;
        this.offer = offer;
        this.demand = demand;
    }
}
