package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.enums.ItemEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
public class Item {

    private UUID id;
    private ItemEnum name;
    private Integer score;

}
