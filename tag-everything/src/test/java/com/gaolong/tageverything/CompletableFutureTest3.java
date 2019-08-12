package com.gaolong.tageverything;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest3 {

    @Test
    public void test() {
        CompletableFuture<String> story_start = CompletableFuture.supplyAsync(() -> {
            System.out.println("老樵夫去山中砍柴, ");
            System.out.println("...");
            sleep(4);
            return "老樵夫";
        }).thenApply(grand_father -> {
            System.out.println(grand_father + "感到天摇地动...");
            System.out.println("...");
            return "老樵夫";
        });

        story_start.thenCombine(CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("穿山甲在山里觅食。");
                    System.out.println("...");
                    sleep(2);
                    return "穿山甲";
                }).thenApply(chuan_shan_jia -> {
                    System.out.println(chuan_shan_jia + "不小心凿穿了深山，放出了山里的妖精。");
                    System.out.println("...");
                    sleep(1);
                    return "穿山甲";
                }),
                (grand_father, chuan_shan_jia) -> {
                    System.out.println("爷爷在山洞里遇到了穿山甲，");
                    System.out.println("穿山甲告诉爷爷，葫芦娃可以打败妖精。");
                    sleep(1);
                    System.out.println("爷爷和穿山甲一起，找到了葫芦籽。");
                    return "葫芦籽";
                })
                .thenAccept(hu_lu_zi -> {
                    sleep(2);
                    System.out.println("...");
                    System.out.println(hu_lu_zi + "发芽");
                    sleep(2);
                    System.out.println("长大...");
                    sleep(2);
                    System.out.println("开花...");
                    sleep(2);
                    System.out.println("终于长成了七个小葫芦。");
                })
                .thenRun(() -> System.out.println("这时，妖怪出现，抓走了爷爷"));

        story_start.join();
        CompletableFuture.runAsync(() -> {
            System.out.println("大娃出生，去救爷爷，结果被妖怪抓住了。");
            sleep(1);
        }).thenRun(() -> {
            System.out.println("二娃出生，去救爷爷，结果被妖怪抓住了。");
            sleep(1);
        }).thenRun(() -> {
            System.out.println("三娃出生，去救爷爷，结果被妖怪抓住了。");
            sleep(1);
        }).thenRun(() -> {
            System.out.println("四娃出生，去救爷爷，结果被妖怪抓住了。");
            sleep(1);
        }).thenRun(() -> {
            System.out.println("五娃出生，去救爷爷，结果被妖怪抓住了。");
            sleep(1);
        }).thenRun(() -> {
            System.out.println("六娃出生，去救爷爷，结果被妖怪抓住了。");
            sleep(1);
        }).thenRun(() -> {
            System.out.println("七娃出生，去救六个哥哥");
            sleep(1);
        }).join();

    }

    private void sleep(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
