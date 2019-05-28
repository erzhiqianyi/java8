package com.erzhiqianyi.java8.refactor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestSample {
    public static void main(String[] args) {
        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
                .peek(item -> System.out.println(item))
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
