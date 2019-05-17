package com.erzhiqianyi.java8.stream;

import com.erzhiqianyi.java8.stream.model.Dish;

import java.util.stream.Stream;

public class FilterSample {
    public static void main(String[] args) {
        simpleFilter();
        filterObject();
    }

    public static void simpleFilter() {
        Stream.of(1, 2, 3, 5, 6, 7, 8, 9)
                .filter(num -> num % 2 == 0)
                .forEach(num -> {
                    System.out.println(num);
                    assert num % 2 == 0;
                });

    }

    public static void filterObject() {
        Stream.of(
                "牛,100,0,MEAT",
                "鳗鱼,500,1,FISH",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .filter(dish -> dish.getType() == Dish.Type.FISH)
                .forEach(dish -> {
                    System.out.println(dish);
                    assert dish.getType() == Dish.Type.FISH;

                });

    }
}
