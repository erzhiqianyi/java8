package com.erzhiqianyi.java8.collect;

import com.erzhiqianyi.java8.stream.model.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

public class PartitionSample {
    public static void main(String[] args) {
        Map<Boolean, List<Dish>> map =
                Stream.of(
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
                        .collect(partitioningBy(Dish::isVegetarian)
                        );
        System.out.println(map);

    }


}
