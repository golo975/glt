package com.gaolong.springbootdemo1;

import com.gaolong.springbootdemo1.util.ExecutorUtils;

import javax.sound.midi.Soundbank;
import java.lang.management.MonitorInfo;
import java.util.concurrent.*;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 1. 创建 CompletableFuture 对象

        ExecutorService executor = Executors.newCachedThreadPool();

//
//        //任务1：洗水壶->烧开水
//        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
//            System.out.println("T1:洗水壶...");
//            sleep(1, TimeUnit.SECONDS);
//            System.out.println("T1:烧开水...");
//            sleep(15, TimeUnit.SECONDS);
//        });

        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶...");
            ExecutorUtils.sleep(1, TimeUnit.SECONDS);
            System.out.println("T2:洗茶杯...");
            ExecutorUtils.sleep(2, TimeUnit.SECONDS);
            System.out.println("T2:拿茶叶...");
            ExecutorUtils.sleep(1, TimeUnit.SECONDS);
            return "龙井";
        });

//        //任务3：任务1和任务2完成后执行：泡茶
//        CompletableFuture f3 = f1.thenCombine(f2, (__, tf) -> {
//            System.out.println("T1:拿到茶叶:" + tf);
//            System.out.println("T1:泡茶...");
//            return "上茶:" + tf;
//        });
//
//        //等待任务3执行结果
//        System.out.println(f3.join());

        // 一次执行结果：T1:洗水壶...T2:洗茶壶...T1:烧开水...T2:洗茶杯...T2:拿茶叶...T1:拿到茶叶:龙井T1:泡茶...上茶:龙井


        // todo ----------------------------------------------------------------------

//
//        // 串行关系
//        f2.thenApply((tea) -> {
//            return tea;
//        }).thenAccept((tea) -> {
//            System.out.println(tea);
//        }).thenRun(() -> {
//            System.out.println(123);
//        });
//
//        // 并行关系：and 关系
//        f2.thenCombine(new CompletableFuture<Long>(), (tea, money) -> {return tea + money;});
//
//        f2.thenAcceptBoth(new CompletableFuture<Long>(), (tea, money) -> {
//            System.out.println(tea + money);
//        });
//
//        f2.runAfterBoth(new CompletableFuture<Long>(), () -> {
//
//        });
//
//        // 并行关系：or 关系
//        f2.applyToEither(new CompletableFuture<>(), (tea) -> {
//            return tea;
//        });
//
//        f2.runAfterEither(new CompletableFuture<>(), () -> {
//
//        });
//
//        f2.acceptEither(new CompletableFuture<>(), (tea) -> {
//            System.out.println(tea);
//        });


        // todo 还缺少异常处理部分

        CompletableFuture<Void> f3 = f2.thenAccept(s -> {
            System.out.println(1/0);
        });

        f3.exceptionally(e -> {
            e.printStackTrace();
            System.out.println("error");
            return null;
        });

        f3.join();


        // todo ----------------------------------------------------------------------


        /*
        关于 thenApply/thenApplyAsync VS thenCompose/thenComposeAsync 的区别：
        其实类似 Stream 中的 map 和 flatMap.
        如果可以直接进行运算，可以直接使用 thenApply/thenApplyAsync
        如果你必须要在 CompletableFuture 的链式操作中添加一个返回值本身就是 CompletableFuture 的步骤，就必须使用 thenCompose/thenComposeAsync
        不然的话，后续的链式操作的结果就是 CompletableFuture<CompletableFuture<>> 了。
        总结起来就是， thenCompose 和 flatMap 的目的是一样的，就是为了 flat ，避免出现多级嵌套。
         */

        CompletableFuture.completedFuture("Hello").thenCompose(s -> new CompletableFuture<>());

        new CompletableFuture<String>().thenCompose(sss -> new CompletableFuture<>());
        new CompletableFuture<String>().thenCombine(new CompletableFuture<String>(), (s1, s2) -> s1);

        ExecutorUtils.shutdown(executor);
    }


}
