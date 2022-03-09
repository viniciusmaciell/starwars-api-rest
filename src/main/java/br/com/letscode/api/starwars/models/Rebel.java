package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

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
//    private List<Long> complaints;

    public Rebel() {
        this.confidenceLevel = 2;
    }

    public void report() {
        confidenceLevel--;
    }
}
