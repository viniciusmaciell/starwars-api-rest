package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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
    @JsonProperty("gender")
    private GenderEnum gender;
    private Location location;
//    private List<Long> complaints;

    public Rebel(String name, Integer age, GenderEnum gender, Location location) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }

    public Rebel() {

    }
//
//        @Override
//    public String toString() {
//        return "RebelModel{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", gender=" + gender +
//                ", location=" + location +
//                '}';
//    }
}
