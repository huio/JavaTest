package cf.fcff.AbstractInterface;

/**
 * Created by H on 2018/9/14.
 *
 * @author H
 */
public class Test {
    public static void main(String[] args) {
        AbstractImplementsInterface abstractImplementsInterface = new AbstractImplementsInterface() {
            @Override
            public void abstractMethod() {
                System.out.println("AbstractImplementsInterface abstractMethod");
            }

            @Override
            public void abstractParam(String s) {
                System.out.println("AbstractImplementsInterface abstractParam:" + s);
            }

            @Override
            public String abstractReturn() {
                System.out.println("AbstractImplementsInterface abstractReturn");
                return null;
            }
        };

        abstractImplementsInterface.interfaceMethod();
        abstractImplementsInterface.interfaceParam("黄金辉");
        abstractImplementsInterface.interfaceReturn();
    }
}
