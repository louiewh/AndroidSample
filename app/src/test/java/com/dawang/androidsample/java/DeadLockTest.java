package com.dawang.androidsample.java;

import org.junit.Test;

public class DeadLockTest {
    private Object objectA;
    private Object objectB;

    @Test
    public void deadLock() throws InterruptedException {
        objectA = 1;
        objectB = 2;
        lockThread(objectA, objectB).start();
        lockThread(objectB, objectA).run();
        System.out.println("DeadLockTest End");
    }

    private Thread lockThread(final Object A, final Object B) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    synchronized (A) {
                        System.out.println("synchronized:" + A);
                        try {
                            synchronized (B) {
                                Thread.sleep(1000);
                                System.out.println("lockThread:" + B);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        return threadA;
    }
}
