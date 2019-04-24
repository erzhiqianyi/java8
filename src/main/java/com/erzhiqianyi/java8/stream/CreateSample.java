package com.erzhiqianyi.java8.stream;

import javax.swing.*;
import javax.swing.plaf.synth.SynthTreeUI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class CreateSample {
    public static void main(String[] args) throws IOException {
        of();
        empty();
        fromArray();
        fromFile();
        iterate();
        generate();
    }

    public static void of() {
        Stream<String> stream = Stream.of("one", "two", "three");
        stream.forEach(System.out::println);
    }

    public static void empty() {
        Stream stream = Stream.empty();
        stream.forEach(System.out::println);
    }

    public static void fromArray() {
        String[] arr = {"one", "two", "three"};
        Stream<String> stream = Arrays.stream(arr);
        stream.forEach(System.out::println);
    }


    public static void fromFile() throws IOException {
        Stream<String> lines = Files.lines( Paths.get("README.md"));
        lines.forEach(System.out::println);
    }

    public static void iterate(){
        Stream.iterate(0,n -> n+2)
                .limit(20)
                .forEach(System.out::println);
    }

    public static void generate(){
        Stream.generate(()-> "one").limit(10).forEach(System.out::println);
    }
}
