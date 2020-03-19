package cf.fcff.execute;

public class Main {

    static {
        System.out.println("Main.static{ ... }");
    }

    public static void main(String[] args) {
        System.out.println("Main.public static void main(String[] args) { ... }");
        System.out.println("----------------------");

        StaticTest.staticMethod();
        System.out.println("----------------------");

        StaticTest staticTest = new StaticTest();
        System.out.println("----------------------");
        staticTest.method();
        System.out.println("----------------------");

        System.out.println("before");
        method(true);
        System.out.println("after");
    }

    private static void method(boolean isTrue) {
        System.out.println("method before");
        if (isTrue) {
            return;
        }
        System.out.println("method after");
    }

    public static void sysOut(String string) {
        System.out.println(string);
    }

    /*
    类的加载顺序：
        如果类还没有被加载：
        1、执行父类的静态代码块和静态变量初始化。(静态代码块和静态变量的执行顺序只跟代码中出现的顺序有关)
        2、执行子类的静态代码块和静态变量初始化。
        3、执行父类的实例变量初始化
        4、执行父类的构造函数
        5、执行子类的实例变量初始化
        6、执行子类的构造函数

        如果类已经被加载：
        则静态代码块和静态变量就不用重复执行，再创建类对象时，只执行与实例相关的变量初始化和构造方法。
     */
}
