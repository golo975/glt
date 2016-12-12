package com.glt.util.kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by gaolong on 2016/12/12.
 */
public class KafkaProducer implements InitializingBean, Closeable {

    private String brokerList;
    private Integer retries = 3;
    private Producer<Integer, String> producer;

    public void init() {
        Properties properties = new Properties();
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list", brokerList);
        properties.put("retries", retries);
        properties.put("acks", "all");// ????
        this.producer = new Producer<>(new ProducerConfig(properties));
    }

    @Override
    public void close() throws IOException {
        this.producer.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }


    // ==========     ==========

    public void sendMsg(String topic, String message) {
        producer.send(new KeyedMessage<Integer, String>(topic, message));
    }

    public void sendMsg(String topic, String message, Integer key) {// key 是一个Integer类型的值
        producer.send(new KeyedMessage<>(topic, key, message));
    }

    public void sendMsg(String topic, String message, Integer key, Object partKey) {
        producer.send(new KeyedMessage<>(topic, key, partKey, message));// todo 这里的 partKey 是什么???
    }


    public void sendMsg(String topic, List<String> message) {
        if (message != null && message.size() > 0) {
            List<KeyedMessage<Integer, String>> listMessage = new ArrayList<>();
            for (String msg : message) {
                listMessage.add(new KeyedMessage<Integer, String>(topic, msg));
            }
            producer.send(listMessage);
        }
    }

    public void sendMsg(String topic, List<String> message, Integer key) {
        if (message != null && message.size() > 0) {
            List<KeyedMessage<Integer, String>> listMessage = new ArrayList<>();
            for (String msg : message) {
                listMessage.add(new KeyedMessage<>(topic, key, msg));
            }
            producer.send(listMessage);
        }
    }

    public void sendMsg(String topic, List<String> message, Integer key, Object partKey) {
        if (message != null && message.size() > 0) {
            List<KeyedMessage<Integer, String>> listMessage = new ArrayList<>();
            for (String msg : message) {
                listMessage.add(new KeyedMessage<>(topic, key, partKey, msg));
            }
            producer.send(listMessage);
        }
    }


    // ========== getter & setter ==========


    public String getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(String brokerList) {
        this.brokerList = brokerList;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }
}
