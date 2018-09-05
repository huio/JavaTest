package com.hui.execute;

public class StaticTest {
    static {
        System.out.println("StaticTest.static {...}");
    }

    public StaticTest() {
        System.out.println("StaticTest.public StaticTest() {...} constructor");
    }

    public void method() {
        System.out.println("StaticTest.public void method()");
    }

    public static void staticMethod() {
        System.out.println("StaticTest.public static void staticMethod()");
    }
}
