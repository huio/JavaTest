package com.hui.Singleton;

public class ClassLoader {
    private static ClassLoader ourInstance = new ClassLoader();

    public static ClassLoader getInstance() {
        return ourInstance;
    }

    private ClassLoader() {
    }
}
