package com.glt.util.kafka.consumer.executor;

import com.glt.util.kafka.consumer.callback.ConsumerCallback;

/**
 * Created by gaolong on 2016/12/12.
 */
public interface Consumer {

    void start();

    void stop();

    void close();

    void execute(ConsumerCallback callback);
}
