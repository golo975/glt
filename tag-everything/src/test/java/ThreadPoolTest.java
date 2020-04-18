import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) {
        // 1. 如何创建一个线程池
        ThreadFactory threadFactory = (runnable) -> {
            return new Thread("");
        };
        ThreadPoolExecutor threadPoolExecutor = ((ThreadPoolExecutor) Executors.newCachedThreadPool(threadFactory));
        threadPoolExecutor.setMaximumPoolSize(100);

        Runnable runnable = () -> {
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        threadPoolExecutor.submit(runnable);

        threadPoolExecutor.shutdown();
        try {
            if (!threadPoolExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPoolExecutor.shutdownNow();
                if (!threadPoolExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    // todo throw exception and logger error
                }
            }
        } catch (InterruptedException e) {
            threadPoolExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
