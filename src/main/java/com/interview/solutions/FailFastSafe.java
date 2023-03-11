package com.interview.solutions;

import java.util.ArrayList;

public class FailFastSafe {

    public static ArrayList list;
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        list = new ArrayList();
        thread1.start();
        thread2.start();
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            FailFastSafe.list.add("Karampudi" + i);
            System.out.println(FailFastSafe.list);
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            FailFastSafe.list.add("Rajasekhar" + i);
            System.out.println(FailFastSafe.list);
        }
    }
}