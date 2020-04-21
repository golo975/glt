package com.gaolong.springbootdemo1;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Semaphore:信号量
 * <p>
 * <p>
 * <p>
 * 类比情景：银行只有4个窗口，但是有很多用户需要办理业务，所以需要排队。
 * <p>
 * 这里每个【用户】都代表一个线程，而【银行】代表需要被多个线程共享的资源
 * <p>
 * <p>
 * <p>
 * 总结：需要注意一点：信号量的角色和锁Lock类似，它被用于多线程共享的对象内部，保证被多线程共享的对象是线程安全的，至于这个对象会被什么线程（来自什么样的线程池）调用，它无法控制。
 */
public class SemaphoreDemo {

    public static void main(String[] args) {


        Bank bank = new Bank(4);// 银行有4个窗口

        // 虽然这里创建了10个线程，但是因为限流器只能同时处理 4 个任务，所以实际上在任意时间最多只有4个线程处于运行状态，多于的线程处于阻塞状态
        ExecutorService consumerThreadPoll = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 40; i++) {
            final int j = i;
            consumerThreadPoll.submit(() -> {
                bank.handle(j + 1);
            });
        }


        consumerThreadPoll.shutdown();
        try {
            if (!consumerThreadPoll.awaitTermination(1000, TimeUnit.SECONDS)) {
                consumerThreadPoll.shutdownNow();
                if (!consumerThreadPoll.awaitTermination(10, TimeUnit.SECONDS)) {
                    throw new RuntimeException("线程池没有正常关闭");
                }
            }
        } catch (InterruptedException e) {
            consumerThreadPoll.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }

    public static class Bank {
        Semaphore semaphore;
        Random random = new Random(System.currentTimeMillis());

        /**
         * @param no 银行的窗口数量，即可以同时办理的任务数
         */
        public Bank(int no) {
            semaphore = new Semaphore(no);
        }

        public void handle(int consumerNo) {// 进来一个消费者
            try {
                semaphore.acquire();
                System.out.println("顾客" + consumerNo + "开始办理");
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // release()之前并不要求已经执行过acquire(), 所以正确使用 semaphore 依赖于在开发时使用正确的惯用法
                semaphore.release();
                System.out.println("顾客" + consumerNo + "完成办理 - over");
            }
        }
    }

}
