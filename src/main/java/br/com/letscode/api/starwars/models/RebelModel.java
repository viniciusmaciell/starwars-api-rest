package br.com.letscode.api.starwars.models;

import br.com.letscode.api.starwars.enums.GenderEnum;
import lombok.Data;

@Data
public class RebelModel {

    private String  name;
    private Integer age;
//    @JsonProperty("gender")
    private GenderEnum gender;
    private LocationModel location;

    public RebelModel(String name, Integer age, GenderEnum gender, LocationModel location) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }

        @Override
    public String toString() {
        return "RebelModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", location=" + location +
                '}';
    }
}