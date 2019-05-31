package com.erzhiqianyi.java8.defmethod;

public class DefaultMethodSample {
    public static void main(String[] args){
        Info info = new InfoImpl();
        info.info();
        Info.hello();
    }
    interface Info {
        default void info() {
            System.out.println("a default method");
        }

        static void hello(){
            System.out.println("a static method");
        }
    }

    static class InfoImpl implements Info{

    }
}
