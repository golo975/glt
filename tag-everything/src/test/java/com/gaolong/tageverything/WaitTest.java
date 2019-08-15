package com.gaolong.tageverything;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class WaitTest {

    public static void main(String[] args) throws InterruptedException {
        WaitTest waitTest = new WaitTest();
        CompletableFuture.runAsync(() -> waitTest.doSomething(1));
        CompletableFuture.runAsync(() -> waitTest.doSomething(2));
        CompletableFuture.runAsync(() -> waitTest.doSomething(3));
        CompletableFuture.runAsync(() -> waitTest.doSomething(4));


        TimeUnit.SECONDS.sleep(10);
    }


    public synchronized void doSomething(int index) {
        try {
            System.out.println(index + "doSomething - start");
            wait(1);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(index + "doSomething - end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
