package com.gaolong.springbootdemo1.util;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorUtils {

    private static Random random = new Random(System.currentTimeMillis());

    public static void shutdown(ExecutorService executorService) {
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

    public static void sleep(long timeout, int randomMills) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
            TimeUnit.MILLISECONDS.sleep(random.nextInt(randomMills));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
