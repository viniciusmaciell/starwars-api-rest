package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    public void hasItems(List<Item> items) {
        if(!this.getInventory().containsAll(items)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You don't have the items to make the deal.");
        }
    }
}
