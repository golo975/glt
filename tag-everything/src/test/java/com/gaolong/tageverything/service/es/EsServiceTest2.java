//package com.gaolong.tageverything.service.es;
//
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(locations = {"classpath:application.xml", "classpath:application.xml"})
//public class EsServiceTest2 {
//
//    @Test
//    public void Test() throws UnknownHostException {
//        final String ES_HOST = "localhost";
//        final int ES_PORT = 9300;
//
//
//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName(ES_HOST), ES_PORT))
//                .addTransportAddress(new TransportAddress(InetAddress.getByName(ES_HOST), ES_PORT));
//
//
//
//
//        GetResponse response = client.prepareGet("twitter", "_doc", "1").get();
//        System.out.println(response);
//
////
//
//
//
//        client.close();
//    }
//
//}
