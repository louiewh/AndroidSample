package com.dawang.androidsample.java;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class UnreentrantLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();


    @Test
    public void test(){
        lock();
        System.err.println("lock 1");
        lock();
        System.err.println("lock 2");
    }

    public void lock() {
        Thread current = Thread.currentThread();
        for (;;) {
            boolean cas  = owner.compareAndSet(null, current);
            System.err.println("lock cas:"+cas);
            if (cas) {
                return;
            }
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        owner.compareAndSet(current, null);
    }
}
