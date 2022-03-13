package br.com.letscode.api.starwars.utils;

public enum ItemEnum {
    WEAPON(4), AMMUNITION(3), WATER(2), FOOD(1);

    private final int value;

    ItemEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
