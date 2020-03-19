package cf.fcff.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class ChildrenSuperBefore extends Parent {
    String className = getClass().getSimpleName();

    @Override
    public void publicMethod() {
        System.out.println(className + " privateSomething()");
        super.publicMethod();
    }
}
