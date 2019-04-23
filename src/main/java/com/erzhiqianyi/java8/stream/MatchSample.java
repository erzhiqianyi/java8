package com.erzhiqianyi.java8.stream;

import java.util.Arrays;
import java.util.List;

public class MatchSample {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three", "four", "five"};
        List<String> list = Arrays.asList(array);
        boolean lengthMatch = list.stream().anyMatch(str -> str.length() > 4);
        assert lengthMatch;

        boolean allMatch = list.stream().allMatch(str -> str.length() > 1);
        assert  allMatch;

        boolean noneMatch = list.stream().noneMatch(str -> str.length() > 10);
        assert  noneMatch;
    }
}
