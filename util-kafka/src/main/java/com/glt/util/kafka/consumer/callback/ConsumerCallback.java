package com.glt.util.kafka.consumer.callback;

/**
 * Created by gaolong on 2016/12/12.
 */
public interface ConsumerCallback {
    void handle(byte[] message);
}
