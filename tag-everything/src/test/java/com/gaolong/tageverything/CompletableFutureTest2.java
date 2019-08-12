package com.gaolong.tageverything;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest2 {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        test1();
        long end = System.currentTimeMillis();
        System.out.println("执行耗时：" + (end - start) / 1000 + "秒");
    }

    private void test1() throws ExecutionException, InterruptedException {
        // (1 + 2) * (3 + 4)
        CompletableFuture<Integer> part1 = CompletableFuture
                .supplyAsync(() -> 1)
                .thenApply(i -> i + 2);
        CompletableFuture<Integer> part2 = CompletableFuture
                .supplyAsync(() -> 3)
                .thenApply(i -> i + 4);


        CompletableFuture<Integer> part3 = part1.thenCombine(
                part2,
                (part1_result, part2_result) -> part1_result * part2_result);
        System.out.println(part3.get());


        CompletableFuture<Integer> part4 = part1.thenCompose(part1_result ->
                CompletableFuture
                        .supplyAsync(() -> 3)
                        .thenApply(i -> i + 4)
                        .thenApply(i -> i * part1_result));
        System.out.println(part4.get());
    }

    void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
