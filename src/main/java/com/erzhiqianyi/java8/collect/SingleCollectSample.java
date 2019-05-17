package com.erzhiqianyi.java8.collect;

import com.erzhiqianyi.java8.stream.model.Dish;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SingleCollectSample {

    public static void main(String[] args) {
        maxBy();
        max();
        minBy();
        min();
        summingDouble();
        averageDouble();
        summarizingDouble();
        joining();
    }

    private static void maxBy() {
        Optional<Dish> dishOptional = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(Collectors.maxBy(Comparator.comparingDouble(Dish::getCalories)));
        System.out.println(dishOptional.get().getCalories());
    }

    private static void minBy() {
        Optional<Dish> dishOptional = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(Collectors.minBy(Comparator.comparingDouble(Dish::getCalories)));
        System.out.println(dishOptional.get().getCalories());
    }

    private static void max() {
        Optional<Dish> dishOptional = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .max(Comparator.comparing(Dish::getCalories));
        System.out.println(dishOptional.get().getCalories());
    }

    private static void min() {
        Optional<Dish> dishOptional = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .min(Comparator.comparing(Dish::getCalories));
        System.out.println(dishOptional.get().getCalories());
    }

    private static void summingDouble() {
        double cal = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(Collectors.summingDouble(Dish::getCalories));
        System.out.println(cal);
    }

    private static void averageDouble() {
        double cal = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(Collectors.averagingDouble(Dish::getCalories));
        System.out.println(cal);
    }

    private static void summarizingDouble() {
        DoubleSummaryStatistics statistics = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(Collectors.summarizingDouble(Dish::getCalories));
        System.out.println(statistics);
    }

    private static void joining() {
        String str = Stream.of("A", "B", "C", "D", "E")
                .collect(Collectors.joining(","));
        System.out.println(str);
    }
}
