package com.hui.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class Parent {

    //只有自己能访问
    private void privateSomething() {
        System.out.println("Parent privateSomething()");
    }

    //同一包下才能访问，同一包下子类能重写
    void defaultSomething() {
        System.out.println("Parent defaultSomething()");
    }
    //同一包下才能访问，子类能重写
    protected void protectedSomething() {
        System.out.println("Parent protectedSomething()");
    }

    public void publicSomething() {
        System.out.println("Parent publicSomething()");
    }

    public String returnSomething() {
        return "Parent returnSomething() return";
    }
}
