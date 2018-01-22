package com.glt.util.kafka.consumer.executor;

import com.glt.util.kafka.consumer.callback.ConsumerCallback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by gaolong on 2016/12/12.
 */
public class ExecutorConfig {
    public String topic;
    public ExecutorService topicExecutor;
    public ConsumerCallback callback;
    public AtomicBoolean topicCosumingFlag = new AtomicBoolean(true);
    public Integer threadNums;

    public ExecutorConfig(String topic, ConsumerCallback callback) {
        this.topic = topic;
        this.callback = callback;
        setExecutors();
    }

    public ExecutorConfig(String topic, ConsumerCallback callback, Integer threadNums) {
        this.topic = topic;
        this.callback = callback;
        this.threadNums = threadNums;
        setExecutors();
    }

    public void setExecutors() {
        if (threadNums != null) {
            threadNums = 1;
        }
        // 一个线程池
        this.topicExecutor = Executors.newFixedThreadPool(threadNums);
    }

    // ========== getter & setter ==========

    public String getTopic() {
        return topic;
    }

    public ExecutorConfig setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public ExecutorService getTopicExecutor() {
        return topicExecutor;
    }

    public ExecutorConfig setTopicExecutor(ExecutorService topicExecutor) {
        this.topicExecutor = topicExecutor;
        return this;
    }

    public ConsumerCallback getCallback() {
        return callback;
    }

    public ExecutorConfig setCallback(ConsumerCallback callback) {
        this.callback = callback;
        return this;
    }

    public AtomicBoolean getTopicCosumingFlag() {
        return topicCosumingFlag;
    }

    public ExecutorConfig setTopicCosumingFlag(AtomicBoolean topicCosumingFlag) {
        this.topicCosumingFlag = topicCosumingFlag;
        return this;
    }
}
