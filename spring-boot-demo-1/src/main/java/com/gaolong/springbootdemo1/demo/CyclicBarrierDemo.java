package com.gaolong.springbootdemo1.demo;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 情景描述详见 CountDownLatchDemo
 * 这里使用 CyclicBarrier 代替 CountDownLatch
 * CountDownLatch 只能用一次，当 count 降到 0 的时候，就没有用了。
 * CyclicBarrier 可以重复使用，在 count 降到 0 的时候，会自动重置为默认值
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Random random = new Random(System.currentTimeMillis());

        Runnable eat = () -> {
            System.out.println("吃了一块奥利奥夹心饼干");
        };

        CyclicBarrier barrier = new CyclicBarrier(3, eat);

        Runnable piece = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(random.nextInt(2000));
                    System.out.println("生产了一片饼干");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable cream = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(random.nextInt(2000));
                    System.out.println("生产了一层奶油");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };

        executorService.submit(piece);
        executorService.submit(piece);
        executorService.submit(cream);

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    throw new RuntimeException("线程池没有正常关闭");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
