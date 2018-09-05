package com.hui.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class ExtendsTest {

    public static void main(String[] args) {

        Children children = new Children();
//        ChildrenSuper children = new ChildrenSuper();
//        ChildrenSuperBefore children = new ChildrenSuperBefore();
//        ChildrenSuperAfter children = new ChildrenSuperAfter();

        children.protectedSomething();
        children.defaultSomething();
        children.publicSomething();
        System.out.println(children.returnSomething());

        Parent parent = new Parent();
        parent.defaultSomething();
        parent.protectedSomething();
        parent.publicSomething();
        parent.returnSomething();
    }
}
