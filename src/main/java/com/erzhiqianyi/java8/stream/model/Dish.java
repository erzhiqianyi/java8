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
        Double calories = Double.valueOf(array[1]);
        boolean vegetarian = array[2].equals("1");
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

    public enum CaloriesLevel {
        DIET,
        NORMAL,
        FAT;

        public static CaloriesLevel level(Double calories) {
            if (calories > 100 ){
                return CaloriesLevel.FAT;
            }else if(calories > 50){
                return CaloriesLevel.NORMAL;
            }else {
                return CaloriesLevel.DIET;
            }
        }
    }
}

