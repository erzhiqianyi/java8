package com.erzhiqianyi.java8.stream;

import java.util.stream.Stream;

public class DistinctSample {
    public static void main(String[] args){
        simpleDistinct();
    }

    public static void simpleDistinct(){
        long count = Stream.of(1,1,1,1,1,1,1,1)
                .distinct()
                .count();
        assert  count == 1;
    }

}
