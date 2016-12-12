package com.glt.util.kafka.consumer;

import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by gaolong on 2016/12/12.
 */
public class KafkaConsumer implements InitializingBean, Closeable {

    private Integer threadNums;
    private String topic;
    private String groupId;

    private String offset;
    private String zkConnect;
    private String zkSessionTimeout;
    private String zkSyncTime;
    private String autoCommitInterval;
    //    private DefaultConsumer;


    public KafkaConsumer() {
        super();
    }

    @Override

    public void close() throws IOException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
