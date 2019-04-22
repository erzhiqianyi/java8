package com.erzhiqianyi.java8.stream;

import com.erzhiqianyi.java8.stream.model.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSample {
    public static void main(String[] args) {
        List<Dish> dishes = Stream.of(
                "牛肉,100,0,MEAT",
                "鳗鱼,500,1,FISH",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT")
                .map(dish -> {
                    String[] array = dish.split(",");
                    String name = array[0];
                    Double calories = Double.valueOf(array[1]);
                    boolean vegetarian = array[2].equals(1);
                    Dish.Type type = Dish.Type.valueOf(array[3]);
                    return new Dish(calories, name, type, vegetarian);
                })
                .collect(Collectors.toList());
        List<String> lowCaloricDishes = getLowCaloricDishesBeforeStream(dishes);
        List<String> lowCaloricDishesByStream = getLowCaloricDishesUseStream(dishes);
        int length = lowCaloricDishes.size();
        for (int i = 0; i < length; i++) {
            System.out.println(Objects.equals(lowCaloricDishes.get(i), lowCaloricDishesByStream.get(i)));
        }
    }

    public static List<String> getLowCaloricDishesBeforeStream(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (Double.compare(dish.getCalories(), 400) == -1) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Double.compare(o1.getCalories(), o2.getCalories());
            }
        });
        List<String> lowCaloricDishName = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishName.add(dish.getName());
        }
        return lowCaloricDishName;
    }

    public static List<String> getLowCaloricDishesUseStream(List<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> Double.compare(dish.getCalories(), 400) == -1)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
