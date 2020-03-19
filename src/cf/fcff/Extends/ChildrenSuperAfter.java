package cf.fcff.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class ChildrenSuperAfter extends Parent {
    String className = getClass().getSimpleName();

    @Override
    public void publicMethod() {
        super.publicMethod();
        System.out.println(className + " privateSomething()");
    }
}
