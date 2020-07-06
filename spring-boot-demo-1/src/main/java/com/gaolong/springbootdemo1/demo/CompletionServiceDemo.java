package com.gaolong.springbootdemo1.demo;

import com.gaolong.springbootdemo1.util.ExecutorUtils;

import java.util.concurrent.*;

public class CompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor, new LinkedBlockingQueue<>(10));
        completionService.submit(() -> {
            ExecutorUtils.sleep(1, 1000);
            return "1: Hello World";
        });
        completionService.submit(() -> {
            ExecutorUtils.sleep(1, 1000);
            return "2: Hello World";
        });
        completionService.submit(() -> {
            ExecutorUtils.sleep(1, 1000);
            return "3: Hello World";
        });
        completionService.submit(() -> {
            ExecutorUtils.sleep(1, 1000);
            return "4: Hello World";
        });

        // take() 方法：如果获取不到，则阻塞
        // poll() 方法：如果获取不到，则返回null，不会阻塞
        for (int i = 0; i < 2; i++) {
            System.out.println(completionService.take().get());
            Future<String> pollFuture = completionService.poll();
            System.out.println(pollFuture);
        }

        ExecutorUtils.shutdown(executor);
    }




}
