package com.erzhiqianyi.java8.collect;

import com.erzhiqianyi.java8.stream.model.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class GroupSample {
    public static void main(String[] args) {
        groupingKey();
        groupingValue();
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
}
