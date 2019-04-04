package com.dawang.androidsample.java;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExecuteServerTest {

    @Test
    public void newCachedThreadPool() throws InterruptedException {
//        ExecutorService service = Executors.newCachedThreadPool();
//        ExecutorService service = Executors.newScheduledThreadPool(10);
        ExecutorService service = Executors.newFixedThreadPool(10);

        service.execute(new Runnable() {
            @Override
            public void run() {
                System.err.println(Thread.currentThread().toString());
//                printAllThread();
            }
        });
        printAllThread();

    }

    private void printAllThread(){
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.enumerate(threadArray);
        for (Thread th: threadArray) {
            System.err.println(th.toString());
        }
    }
}