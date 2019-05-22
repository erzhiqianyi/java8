package com.erzhiqianyi.java8.collect;

import com.erzhiqianyi.java8.stream.model.Dish;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;


public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Collector.Characteristics.IDENTITY_FINISH,
                Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {
        List<Dish> dishes = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT",
                "鸭,10,0,MEAT",
                "苹果,50,1,OTHER",
                "草鱼,50,0,FISH",
                "皖鱼,20,0,FISH",
                "西瓜,10,1,OTHER"
        )
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(new ToListCollector<>());
        dishes.forEach(item -> System.out.println(item));

        dishes = Stream.of(
                "牛,100,0,MEAT",
                "猪肉,500,0,MEAT",
                "鸡,20,0,MEAT",
                "鸭,10,0,MEAT",
                "苹果,50,1,OTHER",
                "草鱼,50,0,FISH",
                "皖鱼,20,0,FISH",
                "西瓜,10,1,OTHER"
        )
                .map(csv -> csv.split(","))
                .map(Dish::new)
                .collect(
                        ArrayList::new,
                        List::add,
                        List::addAll);
        dishes.forEach(item -> System.out.println(item));
    }
}
