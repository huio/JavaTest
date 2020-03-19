package cf.fcff.Singleton;

public class InnerClass {
    private static class SingletonHolder{
        private static final InnerClass INSTANCE = new InnerClass();
    }

    public static InnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private InnerClass() {
    }
}
