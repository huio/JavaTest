package cf.fcff.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class Children extends Parent {
    String className = getClass().getSimpleName();

    private void privateMethod(){
        System.out.println(className + " privateMethod()");
    }


    @Override
    void defaultMethod() {
        System.out.println(className + " defaultMethod()");
    }

    @Override
    protected void protectedMethod() {
        System.out.println(className + " protectedMethod()");
    }

    @Override
    public void publicMethod() {
        System.out.println(className + " publicMethod()");
    }
}
