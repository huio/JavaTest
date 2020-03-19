package cf.fcff.ExtendsTest;

import cf.fcff.Extends.Parent;

/**
 * Created by H on 2018/8/17.
 */
public class ExtendsTest {

    public static void main(String[] args) {

        Children children = new Children();
        children.protectedMethod();
        children.publicMethod();

        Parent parent = new Parent();
//        parent.defaultSomething();//不同包不能访问
//        parent.protectedMethod();//不同包不能访问
        parent.publicMethod();
    }
}
