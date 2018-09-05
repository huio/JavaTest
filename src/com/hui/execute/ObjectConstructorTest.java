package com.hui.execute;

import com.hui.execute.ObjectTest;

public class ObjectConstructorTest {
    private ObjectTest objectTest;

    public ObjectConstructorTest(ObjectTest objectTest) {
        this.objectTest = objectTest;
    }

    public void getName() {
        System.out.println(objectTest.name);
    }
}
