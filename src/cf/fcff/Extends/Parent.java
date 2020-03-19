package cf.fcff.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class Parent {
    String className = getClass().getSimpleName();

    /**
     * 只有自己能访问
     */
    private void privateSomething() {
        System.out.println(className + " privateSomething()");
    }

    /**
     * 同一包下才能访问，同一包下子类能重写
     */
    void defaultMethod() {
        System.out.println(className + " defaultMethod()");
    }

    /**
     * 同一包下才能访问，子类能重写
     */
    protected void protectedMethod() {
        System.out.println(className + " protectedMethod()");
    }

    public void publicMethod() {
        System.out.println(className + " publicMethod()");
    }
}
