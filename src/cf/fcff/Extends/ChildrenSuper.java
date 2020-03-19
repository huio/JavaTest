package cf.fcff.Extends;

/**
 * Created by H on 2018/8/17.
 */
public class ChildrenSuper extends Parent {

    @Override
    void defaultMethod() {
        super.defaultMethod();
    }

    @Override
    protected void protectedMethod() {
        super.protectedMethod();
    }

    @Override
    public void publicMethod() {
        super.publicMethod();
    }

/*
    @Override
    public String returnMethod() {
        return super.returnMethod();
    }
*/
}
