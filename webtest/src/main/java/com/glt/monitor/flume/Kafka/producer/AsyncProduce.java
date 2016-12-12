package com.glt.monitor.flume.Kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by gaolong on 2016/12/12.
 */
public class AsyncProduce {
    public static Producer<String, String> producer;

    static {
        ResourceBundle resource = ResourceBundle.getBundle("kafka");
        Properties props = new Properties();
        props.put("metadata.broker.list", resource.getString("metadata.broker.list"));
        props.put("serializer.class", resource.getString("serializer.class"));
        props.put("partitioner.class", resource.getString("partitioner.class"));
        props.put("producer.type", resource.getString("producer.type"));
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<>(config);

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                producer.close();
            }
        });

    }

    public static void send(String topic, String msg) {
       try {
           KeyedMessage<String, String> data = new KeyedMessage<>(topic, msg);
           producer.send(data);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
