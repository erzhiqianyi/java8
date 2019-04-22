package com.erzhiqianyi.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapSample {
    public static void main(String[] args) {
        simpleFlatMap();
        twoStreamFlatMap();
    }

    public static void simpleFlatMap() {
        Stream.of("this", "is", "a", "string", "list")
                .map(str -> str.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted()
                .forEach(c -> System.out.println(c));
    }

    public static void twoStreamFlatMap() {
        List<Integer> one = Arrays.asList(1, 2);
        List<Integer> two = Arrays.asList(3, 4);
        List<Integer> three = Arrays.asList(5);
        one.stream().flatMap(i ->
                two.stream()
                        .flatMap(j -> three.stream().map(k -> new int[]{i,j,k})))
                .collect(Collectors.toList())
                .forEach(arr -> System.out.println(Arrays.toString(arr)));

    }
}
