package com.glt.util.kafka.consumer.executor.support;

import com.glt.util.kafka.consumer.callback.ConsumerCallback;
import com.glt.util.kafka.consumer.executor.Consumer;
import com.glt.util.kafka.consumer.executor.ExecutorConfig;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by gaolong on 2016/12/12.
 */
public class DefaultConsumer implements Consumer {

    private ExecutorConfig executorConfig;
    private String groupId;
    private String topic;
    private ConsumerConfig consumerConfig;
    private ConsumerConnector consumerConnector;
    private Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap;
    private Map<String, Integer> topicCountMap = new ConcurrentHashMap<>();
    private Object object = new Object();

    public DefaultConsumer(ConsumerConfig consumerConfig, String groupId, String topic, Integer threadNums) {
        super();
        this.consumerConfig = consumerConfig;
        this.groupId = groupId;
        this.topic = topic;
        this.topicCountMap.put(topic, new Integer(threadNums));
        init();
    }

    public void init() { // todo 有两套Kafka的包吗???
        this.consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(this.consumerConfig);
    }

    @Override
    public void start() {
        if (this.executorConfig != null) {
            this.executorConfig.setTopicCosumingFlag(new AtomicBoolean(true));// todo 为什么要用AtomicBoolean ?? 这样写对吗????
        }
    }

    @Override
    public void stop() {
        if (this.executorConfig != null) {
            this.executorConfig.setTopicCosumingFlag(new AtomicBoolean(false));
        }
    }

    @Override
    public void close() {
        if (this.consumerConnector != null) {
            this.consumerConnector.shutdown();
        }
    }

    @Override
    public void execute(ConsumerCallback callback) {

    }

    public void createKafkaStreams() {// TODO kafka 的 stream API 是什么?????
        synchronized (object) {
            this.consumerMap = this.consumerConnector.createMessageStreams(this.topicCountMap);
        }
    }

    public void dealWithTopic(ConsumerCallback callback) {
        this.executorConfig = new ExecutorConfig(topic, callback, this.topicCountMap.get(topic).intValue());
        execute();
    }

    public void execute() {
        if (this.consumerMap == null || this.consumerMap.isEmpty()) {
            createKafkaStreams();
        }
        List<KafkaStream<byte[], byte[]>> topicStreams = this.consumerMap.get(this.executorConfig.getTopic());
        if (topicStreams == null || topicStreams.size() == 0) {
            throw new RuntimeException(String.format("topic %s can't generate message stream", this.executorConfig.getTopic()));
        }
        for (final KafkaStream<byte[], byte[]> kafkaStream : topicStreams) {
            this.executorConfig.getTopicExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
                    while (iterator.hasNext() && DefaultConsumer.this.executorConfig.getTopicCosumingFlag().get()) {
                        DefaultConsumer.this.executorConfig.getCallback().handle(iterator.next().message());
                    }
                }
            });
        }
    }

}
