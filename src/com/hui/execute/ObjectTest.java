package com.hui.execute;

public class ObjectTest {

    public String name;
    private ObjectConstructorTest objectConstructorTest;

    public ObjectTest() {
    }

    public ObjectTest(String name) {
        this.name = name;
        objectConstructorTest = new ObjectConstructorTest(this);
    }

    public ObjectTest(String name,ObjectConstructorTest objectConstructorTest) {
        this.name = name;
        this.objectConstructorTest = objectConstructorTest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getObjectConstructorName() {
        objectConstructorTest.getName();
    }
}
