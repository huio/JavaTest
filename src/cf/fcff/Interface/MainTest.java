package cf.fcff.Interface;

public class MainTest {

    public static void main(String[] args) {

        InterfaceImpl interfaceImpl = new InterfaceImpl("InterfaceImpl instance");

        InterfaceTest interfaceTest = new InterfaceTest();
        interfaceTest.setAnInterface(interfaceImpl);
        interfaceTest.doSomething();

//        interfaceImpl.doing();

    }
}
