package com.gaolong.springbootdemo1.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Counter counter = new Counter();
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.plusOne();
            }
        };
        threadPool.submit(runnable);
        threadPool.submit(runnable);
        threadPool.submit(runnable);
        threadPool.submit(runnable);
        threadPool.submit(runnable);

        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                    throw new RuntimeException("线程池没有正常关闭");
                }
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println(counter.getCount());
    }

    public static class Counter {
        private int count = 0;

        ReentrantLock reentrantLock = new ReentrantLock();

        public void plusOne() {
            reentrantLock.lock();

            try {
                count++;
            } finally {
                reentrantLock.unlock();
            }
        }

        public int getCount() {// TODO 这个方法需要加锁吗 ？ 如果不加锁的话，可以用 volatile 关键字代替 （ ？）
            reentrantLock.lock();
            try {
                return count;
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}
