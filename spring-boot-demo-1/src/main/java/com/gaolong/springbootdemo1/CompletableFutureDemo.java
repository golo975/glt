package com.gaolong.springbootdemo1;

import com.gaolong.springbootdemo1.util.ExecutorUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        // 1. 创建 CompletableFuture 对象

        ExecutorService executor = Executors.newCachedThreadPool();
//
//        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
//            return "Hello";
//        });
//        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
//            return "Hello";
//        }, executor);
//
//        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(() -> {
//            System.out.println("Hello World");
//        });
//
//        CompletableFuture<Void> completableFuture4 = CompletableFuture.runAsync(() -> {
//            System.out.println("Hello World");
//        }, executor);
//
//        CompletableFuture<String> completableFuture5 = CompletableFuture.completedFuture("over");
//
//        CompletableFuture completableFuture6 = new CompletableFuture();
//
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3);// and 的关系
//        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(completableFuture4, completableFuture5, completableFuture6);// or 的关系

        CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenCompose((arg1) -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println(arg1 + " World");
                return arg1 + " World";
            });
        });


        CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenApply((arg1) -> {
            return "";
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExecutorUtils.shutdown(executor);
    }
}
