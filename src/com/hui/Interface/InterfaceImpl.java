package com.hui.Interface;

public class InterfaceImpl implements Interface {
    private String string;

    public InterfaceImpl(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getStrings() {
        System.out.println("getStrings");
        return this.string;
    }

    @Override
    public void before() {
        System.out.println("before " + string);
    }

    @Override
    public void doing() {
        System.out.println("doing " + string);
    }

    @Override
    public void after() {
        System.out.println("after " + string);
    }

    @Override
    public String get() {
        System.out.println("get " + string);
        return this.string;
    }
}
