package com.hui.Thread;

public class ThreadTest extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("ThreadTest run(){...}");
    }
}
