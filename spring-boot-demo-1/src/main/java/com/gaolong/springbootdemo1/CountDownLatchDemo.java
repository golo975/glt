package com.gaolong.springbootdemo1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 情景描述：
 * CountDownLatch 相当于【生产者&消费者】模型的升级版
 * 在【生产者&消费者】模型中，生产者只生产一种东西，消费者也只消费一种东西；
 * 在CountDownLatch中，有多个生产者，每种生产者生产不同的东西，消费者只有在凑齐一定比例的所有类型商品时，才能消费。
 * 比如：
 * 生产者A生产奥利奥饼干，
 * 生产者B生产奥利奥的奶油夹心，
 * 消费者C得到2片奥利奥饼干和一层夹心后，才能组合成一个完整的奥利奥夹心饼干，并消费掉。
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {
            // Latch: 门闩，插销
            CountDownLatch latch = new CountDownLatch(3);

            Runnable piece = () -> {
                try {
                    Thread.sleep(random.nextInt(2000));
                    System.out.println("生产了一片饼干");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            };

            Runnable cream = () -> {
                try {
                    Thread.sleep(random.nextInt(2000));
                    System.out.println("生产了一层奶油");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            };

            executorService.submit(piece);
            executorService.submit(piece);
            executorService.submit(cream);

            try {
                latch.await();
                System.out.println("吃了一块奥利奥夹心饼干");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
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
