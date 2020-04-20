import java.util.concurrent.*;

public class ProducerConsumerTest {

    public static class BusinessQueue {
        private BlockingQueue<String> queue = new LinkedBlockingQueue<>(100);
        private ExecutorService producerThreadPool = initThreadPool(10);
        private ExecutorService consumerThreadPool = initThreadPool(10);

        private ExecutorService initThreadPool(int maximumPoolSize) {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
            threadPoolExecutor.setMaximumPoolSize(maximumPoolSize);
            return threadPoolExecutor;
        }

        void producer(Producer producer) {
            Runnable runnable = () -> {
                queue.add(producer.producer());
            };
            producerThreadPool.submit(runnable);
        }

        void consume(Consumer consumer) {
            Runnable runnable = () -> {
                consumer.consume(queue.poll());
            };
            consumerThreadPool.submit(runnable);
        }
    }

    public interface Producer {
        String producer();
    }

    public interface Consumer {
        void consume(String str);
    }
}
