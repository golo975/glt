package com.glt.monitor.flume;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;

/**
 * Created by gaolong on 2016/12/12.
 */
public class SinkTest extends AbstractSink implements Configurable {

    private String topic;

    @Override
    public void configure(Context context) {
        this.topic = context.getString("topic");
    }


    @Override
    public Status process() throws EventDeliveryException {
        Status result = Status.READY;
        Channel ch = getChannel();
        Transaction tx = ch.getTransaction();
        try {
            tx.begin();
            Event event = ch.take();

            if (event != null) {
                byte[] data = event.getBody();
                if (data != null) {
                    String body = new String(data, "utf-8");
                    System.out.println(body);// todo    AsyncProduce.send(topic.mdg)
                }
            }
        } catch (Exception e) {

        } finally {
            tx.close();
        }
        return result;
    }

}
