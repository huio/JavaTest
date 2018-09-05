package com.hui.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class Children extends Parent {
    String className = getClass().getSimpleName();

    @Override
    void defaultSomething() {
        System.out.println(className + "privateSomething()");
    }

    @Override
    protected void protectedSomething() {
        System.out.println(className + "privateSomething()");
    }

    @Override
    public void publicSomething() {
        System.out.println(className + "privateSomething()");
    }

    @Override
    public String returnSomething() {
        return "Children returnSomething() return";
    }
}
