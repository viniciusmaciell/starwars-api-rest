package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.utils.ItemEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class Item {
    @NotNull
    private UUID id;
    @NotNull
    private ItemEnum name;
    @NotNull
    private Integer score;

}
