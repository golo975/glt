package com.gaolong.springbootdemo1;

import com.gaolong.springbootdemo1.util.ExecutorUtils;

import javax.sound.midi.Soundbank;
import java.lang.management.MonitorInfo;
import java.util.concurrent.*;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 1. 创建 CompletableFuture 对象

        ExecutorService executor = Executors.newCachedThreadPool();


        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1:洗水壶...");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T1:烧开水...");
            sleep(15, TimeUnit.SECONDS);
        });

        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶...");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T2:洗茶杯...");
            sleep(2, TimeUnit.SECONDS);
            System.out.println("T2:拿茶叶...");
            sleep(1, TimeUnit.SECONDS);
            return "龙井";
        });

        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });

        //等待任务3执行结果
        System.out.println(f3.join());

        // 一次执行结果：T1:洗水壶...T2:洗茶壶...T1:烧开水...T2:洗茶杯...T2:拿茶叶...T1:拿到茶叶:龙井T1:泡茶...上茶:龙井



        // todo ----------------------------------------------------------------------
        // todo ----------------------------------------------------------------------
        // todo ----------------------------------------------------------------------
        // todo ----------------------------------------------------------------------


        // 串行关系
        f2.thenApply((tea) -> {
            return tea;
        }).thenAccept((tea) -> {
            System.out.println(tea);
        }).thenRun(() -> {
            System.out.println(123);
        });

        // 并行关系：and 关系
        f2.thenCombine(new CompletableFuture<Long>(), (tea, money) -> {return tea + money;});

        f2.thenAcceptBoth(new CompletableFuture<Long>(), (tea, money) -> {
            System.out.println(tea + money);
        });

        f2.runAfterBoth(new CompletableFuture<Long>(), () -> {

        });

        // 并行关系：or 关系
        f2.applyToEither(new CompletableFuture<>(), (tea) -> {
            return tea;
        });

        f2.runAfterEither(new CompletableFuture<>(), () -> {

        });

        f2.acceptEither(new CompletableFuture<>(), (tea) -> {
            System.out.println(tea);
        });


        // todo 还缺少异常处理部分



        ExecutorUtils.shutdown(executor);
    }

    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
