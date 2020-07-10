package com.gaolong.springbootdemo1;

import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

public class SpringSourceTest {

    @Test
    public void stopWatchTest() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("task_1");

        TimeUnit.MILLISECONDS.sleep(1024);
        stopWatch.stop();

        System.out.println(stopWatch.getLastTaskName());
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }
}
