package com.erzhiqianyi.java8.stream;

import java.util.Arrays;
import java.util.List;

public class FindSample {

    public static void main(String[] args){
        String[] array = {"one", "two", "three", "four", "five"};
        List<String> list = Arrays.asList(array);
        String any = list.stream().findAny().get();
        assert  null != any;

        String first = list.stream().findFirst().get();
        assert  first.equalsIgnoreCase(args[0]);
    }
}
