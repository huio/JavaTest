package com.hui.ExtendsTest;

import com.hui.Extends.Parent;

/**
 * Created by H on 2018/8/17.
 */
public class ExtendsTest {

    public static void main(String[] args) {

        Children children = new Children();
        children.protectedSomething();
        children.publicSomething();
        System.out.println(children.returnSomething());

        Parent parent = new Parent();
//        parent.defaultSomething();//不同包不能访问
//        parent.protectedSomething();//不同包不能访问
        parent.publicSomething();
        parent.returnSomething();
    }
}
