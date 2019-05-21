package com.erzhiqianyi.java8.collect;

import com.erzhiqianyi.java8.stream.model.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class GroupSample {
    public static void main(String[] args) {
        groupingKey();
        groupingValue();
        multiGrouping();
        groupingCount();
        groupingMax();
        groupingMin();
        collectorThan();

    }

    private static void groupingKey() {
        Map<Dish.Type, List<Dish>> typeListMap = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT",
                "鸡,20,0,MEAT",
                "苹果,20,0,OTHER",
                "草鱼,20,0,FISH",
                "皖鱼,20,0,FISH",
                "西瓜,20,0,OTHER"
        )
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(groupingBy(Dish::getType));
        System.out.println(typeListMap);
    }

    private static void groupingValue() {
        Map<Dish.CaloriesLevel, List<Dish>> caloriesLevelListMap = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT",
                "鸡,20,0,MEAT",
                "苹果,20,0,OTHER",
                "草鱼,20,0,FISH",
                "皖鱼,20,0,FISH",
                "西瓜,20,0,OTHER"
        )
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(groupingBy(dish -> Dish.CaloriesLevel.level(dish.getCalories())
                ));
        System.out.println(caloriesLevelListMap);
    }

    private static void multiGrouping() {
        Map<Dish.Type, Map<Dish.CaloriesLevel, List<Dish>>> map =
                Stream.of(
                        "牛,100,0,MEAT",
                        "猪肉,500,0,MEAT",
                        "鸡,20,0,MEAT",
                        "鸭,10,0,MEAT",
                        "苹果,20,0,OTHER",
                        "草鱼,50,0,FISH",
                        "皖鱼,20,0,FISH",
                        "西瓜,50,0,OTHER"
                )
                        .map(csv -> csv.split(","))
                        .map(Dish::new)
                        .collect(
                                groupingBy(Dish::getType,
                                        groupingBy(
                                                dish -> Dish.CaloriesLevel.level(dish.getCalories()))
                                )
                        );
        System.out.println(map);
    }

    private static void groupingCount() {
        Map<Dish.Type, Long> map =
                Stream.of(
                        "牛,100,0,MEAT",
                        "猪肉,500,0,MEAT",
                        "鸡,20,0,MEAT",
                        "鸡,20,0,MEAT",
                        "苹果,20,0,OTHER",
                        "草鱼,20,0,FISH",
                        "皖鱼,20,0,FISH",
                        "西瓜,20,0,OTHER"
                )
                        .map(csv -> csv.split(","))
                        .map(Dish::new)
                        .collect(
                                groupingBy(Dish::getType, counting())
                        );
        System.out.println(map);
    }

    private static void groupingMax() {
        Map<Dish.Type, Optional<Dish>> map =
                Stream.of(
                        "牛,100,0,MEAT",
                        "猪肉,500,0,MEAT",
                        "鸡,20,0,MEAT",
                        "鸭,10,0,MEAT",
                        "苹果,50,0,OTHER",
                        "草鱼,50,0,FISH",
                        "皖鱼,20,0,FISH",
                        "西瓜,10,0,OTHER"
                )
                        .map(csv -> csv.split(","))
                        .map(Dish::new)
                        .collect(
                                groupingBy(Dish::getType,
                                        maxBy(Comparator.comparingDouble(Dish::getCalories))
                                )
                        );
        System.out.println(map);

    }

    private static void groupingMin() {
        Map<Dish.Type, Optional<Dish>> map =
                Stream.of(
                        "牛,100,0,MEAT",
                        "猪肉,500,0,MEAT",
                        "鸡,20,0,MEAT",
                        "鸭,10,0,MEAT",
                        "苹果,20,0,OTHER",
                        "草鱼,50,0,FISH",
                        "皖鱼,20,0,FISH",
                        "西瓜,10,0,OTHER"
                )
                        .map(csv -> csv.split(","))
                        .map(Dish::new)
                        .collect(
                                groupingBy(Dish::getType,
                                        minBy(Comparator.comparingDouble(Dish::getCalories))
                                )
                        );
        System.out.println(map);

    }

    private static void collectorThan() {
        Map<Dish.Type, Dish> map =
                Stream.of(
                        "牛,100,0,MEAT",
                        "猪肉,500,0,MEAT",
                        "鸡,20,0,MEAT",
                        "鸭,10,0,MEAT",
                        "苹果,50,0,OTHER",
                        "草鱼,50,0,FISH",
                        "皖鱼,20,0,FISH",
                        "西瓜,10,0,OTHER"
                )
                        .map(csv -> csv.split(","))
                        .map(Dish::new)
                        .collect(
                                groupingBy(Dish::getType,
                                    collectingAndThen(
                                            maxBy(Comparator.comparingDouble(Dish::getCalories)) ,
                                            Optional::get
                                    )
                                )
                        );
        System.out.println(map);

    }
}
