package com.gaolong.springbootdemo1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {


    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        CacheNoLock cache = new CacheNoLock();// 没加锁的代码，初始化了多次
//        Cache cache = new Cache();// 加了锁的代码，只初始化了一次
        Runnable runnableGet = () -> {
            for (int i = 0; i < 1000; i++) {
                cache.get("abc");
            }
        };
        threadPool.submit(runnableGet);
        threadPool.submit(runnableGet);
        threadPool.submit(runnableGet);
        threadPool.submit(runnableGet);
        threadPool.submit(runnableGet);

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

//        System.out.println(cache.get("abc"));
    }

    public static class CacheNoLock {
        private Map<String, String> cache = new HashMap<>();

        public String get(String key) {
            String value = cache.get(key);
            if (value != null) {
                return value;
            }

            try {
                Thread.sleep(5L);
                cache.put(key, "xyz");
                System.out.println("缓存初始化。");
                Thread.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            value =  cache.get(key);
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return value;
        }
    }
    public static class Cache {
        private Map<String, String> cache = new HashMap<>();

        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Lock writeLock = lock.writeLock();
        private final Lock readLock = lock.readLock();


        public String get(String key) {
            readLock.lock();
            String value;
            try {
                value = cache.get(key);
            } finally {
                readLock.unlock();
            }
            if (value != null) {
                return value;
            }

            writeLock.lock();
            try {
                value = cache.get(key);
                if (value == null) {
                    Thread.sleep(5L);
                    cache.put(key, "xyz");
                    System.out.println("缓存初始化。");
                    Thread.sleep(5L);
                    readLock.lock();
                    value = cache.get(key);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }

            try {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                readLock.unlock();
            }

            return value;
        }
    }
}
