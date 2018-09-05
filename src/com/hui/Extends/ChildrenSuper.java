package com.hui.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class ChildrenSuper extends Parent {

    @Override
    void defaultSomething() {
        super.defaultSomething();
    }

    @Override
    protected void protectedSomething() {
        super.protectedSomething();
    }

    @Override
    public void publicSomething() {
        super.publicSomething();
    }

    @Override
    public String returnSomething() {
        return super.returnSomething();
    }
}
