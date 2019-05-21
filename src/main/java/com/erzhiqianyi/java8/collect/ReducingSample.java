package com.erzhiqianyi.java8.collect;

import com.erzhiqianyi.java8.stream.model.Dish;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.reducing;

public class ReducingSample {

    public static void main(String[] args) {
        counting();
        max();
        min();
    }

    private static void counting() {
        double totalCalories = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(reducing(
                        0,
                        Dish::getCalories,
                        (x, y) -> x.doubleValue() + y.doubleValue()))
                .doubleValue();
        System.out.println("总热量: " + totalCalories);

    }

    private static void max() {
        Optional<Dish> maxCalories = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(reducing(
                        (d1, d2) -> Double.compare(d1.getCalories(), d2.getCalories()) >= 0 ? d1 : d2)
                );
        System.out.println("热量最高: " + maxCalories.get().getCalories());

    }

    private static void min() {
        Optional<Dish> maxCalories = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(reducing(
                        (d1, d2) -> Double.compare(d1.getCalories(), d2.getCalories()) >= 0 ? d2 : d1)
                );
        System.out.println("热量低: " + maxCalories.get().getCalories());


    }
}
