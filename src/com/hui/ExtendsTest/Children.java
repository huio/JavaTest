package com.hui.ExtendsTest;

import com.hui.Extends.Parent;

/**
 * Created by H on 2018/8/17.
 */
public class Children extends Parent {
    String className = getClass().getSimpleName();

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
