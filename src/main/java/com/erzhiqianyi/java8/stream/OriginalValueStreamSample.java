package com.erzhiqianyi.java8.stream;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class OriginalValueStreamSample {
    public static void main(String[] args) {
        IntStream.of(1, 2, 3, 4).forEach(num -> System.out.println(num));
        DoubleStream.of(1, 2, 3, 4).forEach(num -> System.out.println(num));
        LongStream.of(1, 2, 3, 4).forEach(num -> System.out.println(num));
        Stream.of("1", "2", "3", "4").mapToInt(num -> Integer.parseInt(num)).forEach(num -> System.out.println(num));
        Stream.of("1", "2", "3", "4").mapToDouble(num -> Double.parseDouble(num)).forEach(num -> System.out.println(num));
        Stream.of("1", "2", "3", "4").mapToLong(num -> Long.parseLong(num)).forEach(num -> System.out.println(num));
        Stream<Integer> stream = IntStream.of(1, 2, 3, 4, 5).boxed();
        stream.forEach(num -> System.out.println(num.getClass()));
        int rangeSum = IntStream.range(1, 100).sum();
        int rangeClosedSum = IntStream.rangeClosed(1, 100).sum();
        assert rangeClosedSum > rangeSum;
    }
}
