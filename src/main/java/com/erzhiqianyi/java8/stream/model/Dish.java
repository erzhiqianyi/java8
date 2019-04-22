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


    public Dish(Double calories, String name, Type type, boolean vegetarian) {
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
