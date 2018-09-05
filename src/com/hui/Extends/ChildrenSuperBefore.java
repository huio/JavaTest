package com.hui.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class ChildrenSuperBefore extends Parent {
    String className = getClass().getSimpleName();

    @Override
    public void publicSomething() {
        System.out.println(className + " privateSomething()");
        super.publicSomething();
    }
}
