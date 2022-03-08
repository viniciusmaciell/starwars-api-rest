package br.com.letscode.api.starwars.enums;

public enum GenderEnum {
    MALE("Male"),
    FEMALE("Female");

    private String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender;
    }
}
