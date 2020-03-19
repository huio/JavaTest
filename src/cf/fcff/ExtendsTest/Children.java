package cf.fcff.ExtendsTest;

import cf.fcff.Extends.Parent;

/**
 * Created by H on 2018/8/17.
 */
public class Children extends Parent {
    String className = getClass().getSimpleName();

    @Override
    protected void protectedMethod() {
        System.out.println(className + "privateSomething()");
    }

    @Override
    public void publicMethod() {
        System.out.println(className + "privateSomething()");
    }
}
