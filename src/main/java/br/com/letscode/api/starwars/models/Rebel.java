package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Rebel {

    private UUID id;
    private String name;
    private Integer age;
    private GenderEnum gender;
    private Integer confidenceLevel;
    private Location location;
    private List<Item> inventory;
//    private List<Long> complaints;

    public Rebel() {
        this.confidenceLevel = 2;
    }

    public void report() {
        confidenceLevel--;
    }

    public boolean hasItems(List<Item> items) {
        return this.getInventory().containsAll(items);
    }
}
