package com.dawang.androidsample.java;

import android.os.SystemClock;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumer {
    private BlockingDeque<String> mBlockingDeque = new LinkedBlockingDeque();


    @Test
    public void testProducerConsumer(){

        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        String random = new Random().toString();
                        mBlockingDeque.put(random);
                        System.out.println("put:"+random);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        String value = mBlockingDeque.take();
                        System.out.println(" \u001b[31m take: + \u001b[0m" +value);

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        producerThread.start();
        consumerThread.run();
    }
}
