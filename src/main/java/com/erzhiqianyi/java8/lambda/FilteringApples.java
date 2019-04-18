package com.erzhiqianyi.java8.lambda;

import com.erzhiqianyi.java8.lambda.model.Apple;
import com.erzhiqianyi.java8.lambda.model.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class FilteringApples {
    public static void main(String[] args) {
    }

    /**
     * before java8
     */
    public static List<Apple> filterGreenApple(List<Apple> apples) {
        List<Apple> greenApples = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (apple.getColor() == Color.GREEN) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }

    /**
     * before java8 ,a littler better  , color as param
     */
    public static List<Apple> filterAppleByColor(List<Apple> apples, Color color) {
        List<Apple> colorApple = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (color == apple.getColor()) {
                colorApple.add(apple);
            }
        }
        return colorApple;
    }

    /**
     * before java8 ,bad idea ,repeat code
     */
    public static List<Apple> filterAppleByWeight(List<Apple> apples, int weight) {
        List<Apple> weightApple = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (apple.getWeight() > weight) {
                weightApple.add(apple);
            }

        }
        return weightApple;
    }

    /**
     * before java8 , filter color or weight bad idea ,
     */
    public static List<Apple> filterApple(List<Apple> apples, Color color, int weight, boolean flag) {
        List<Apple> colorOrWeightApple = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if ( (flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)){
               colorOrWeightApple.add(apple) ;
            }
        }
        return colorOrWeightApple;
    }

    /**
     * Behavioral parameterization
     */
    public static List<Apple> filterApple(List<Apple> apples , ApplePredicate predicate){
        List<Apple> predicateApple = new ArrayList<Apple>();
        for (Apple apple : apples){
            if (predicate.test(apple)){
                predicateApple.add(apple);
            }
        }

        return predicateApple;
    }


    /**
     *
     *a common filter
     */
    public static <T> List<T> filter(List<T> original ,Predicate predicate){
       List<T> filter = new ArrayList<T>();
       for (T t : original){
           if (predicate.test(t)){
               filter.add(t);
           }
       }
       return filter;
    }
    interface  ApplePredicate{
        boolean test(Apple apple);
    }

    interface  Predicate<T>{
        boolean test(T t);
    }

}
