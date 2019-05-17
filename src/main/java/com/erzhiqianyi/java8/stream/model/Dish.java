package com.erzhiqianyi.java8.stream.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Dish {
    private final Double calories;
    private final String name;
    private final Type type;
    private final boolean vegetarian;



    public Dish(String[] array) {
        String name = array[0];
        boolean vegetarian = array[2].equals(1);
        Double calories = Double.valueOf(array[1]);
        Dish.Type type = Dish.Type.valueOf(array[3]);

        this.calories = calories;
        this.name = name;
        this.type = type;
        this.vegetarian = vegetarian;

    }


    public enum Type {
        MEAT,
        FISH,
        OTHER
    }
}
