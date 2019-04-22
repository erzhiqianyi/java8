package com.erzhiqianyi.java8.stream;

import java.util.Arrays;
import java.util.List;

public class FindExample {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three", "four", "five"};
        List<String> list = Arrays.asList(array);
        boolean lengthMatch = list.stream().anyMatch(str -> str.length() > 4);
        assert lengthMatch;
    }
}
