package com.gaolong.tageverything;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceTest {

    @Test
    public void Test() {
        CompletionService<String> completionService = new ExecutorCompletionService<>(Executors.newCachedThreadPool());

        int task_count = 4;
        List<Future<String>> futures = new ArrayList<>(task_count);
        futures.add(completionService.submit(() -> { TimeUnit.SECONDS.sleep(1);return "a"; }));
        futures.add(completionService.submit(() -> { TimeUnit.SECONDS.sleep(2);return "b"; }));
        futures.add(completionService.submit(() -> { TimeUnit.SECONDS.sleep(3);return "c"; }));
        futures.add(completionService.submit(() -> { TimeUnit.SECONDS.sleep(4);return "d"; }));


        try {
            for (int i = 0; i < task_count; i++) {
                String result = completionService.take().get();
                System.out.println(result);
//                if (result != null) {
//                    break;
//                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            for (Future<String> future : futures) {
                future.cancel(true);
            }
        }
    }
}
