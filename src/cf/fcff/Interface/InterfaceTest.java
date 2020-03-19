package cf.fcff.Interface;

public class InterfaceTest {
    private Interface anInterface;

/*
    public InterfaceTest(Interface anInterface) {
        this.anInterface = anInterface;
    }
*/

    public void setAnInterface(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public void doSomething(){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                if (anInterface != null) {
                    anInterface.get();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                if (anInterface != null) {
                    anInterface.doing();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                if (anInterface != null) {
                    anInterface.after();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                if (anInterface != null) {
                    anInterface.before();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
