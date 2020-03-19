package cf.fcff.AbstractInterface;

/**
 * Created by H on 2018/9/14.
 *
 * @author H
 */
public abstract class AbstractImplementsInterface implements Interface {

    public abstract void abstractMethod();

    public abstract void abstractParam(String s);

    public abstract String abstractReturn();

    @Override
    public void interfaceMethod() {
        System.out.println("Interface interfaceMethod");
        abstractMethod();
    }

    @Override
    public void interfaceParam(String s) {
        System.out.println("Interface interfaceParam:" + s);
        abstractParam(s);
    }

    @Override
    public String interfaceReturn() {
        System.out.println("Interface interfaceReturn");
        return abstractReturn();
    }
}
