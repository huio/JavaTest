package com.hui.Singleton;

public class DoubleCheckedLocking {
    private volatile static DoubleCheckedLocking ourInstance;

    public static DoubleCheckedLocking getInstance() {
        if (ourInstance == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (ourInstance == null) {
                    ourInstance = new DoubleCheckedLocking();
                }
            }
        }
        return ourInstance;
    }

    private DoubleCheckedLocking() {
    }
}
