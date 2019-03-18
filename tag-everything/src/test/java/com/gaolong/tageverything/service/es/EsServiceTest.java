package com.gaolong.tageverything.service.es;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml", "classpath:application.xml"})
public class EsServiceTest {

    @Autowired
    private EsService esService;

    private TransportClient client;

    @Before
    public void initClient() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
    }


    @Test
    public void indexTest() {
        IndexResponse response = esService.index(client);
        System.out.println(response);
        /**
        IndexResponse[
            index=twitter,
            type=tweet,
            id=1,
            version=5,
            result=updated,
            shards={
                "total": 2,
                "successful": 1,
                "failed": 0
            }
        ]
         */
    }

    @Test
    public void getDocumentByIdTest() {
        GetResponse getResponse = esService.getDocumentById(client);
        System.out.println(getResponse);
        /**
        GetResponse : {
            "_index": "twitter",
            "_type": "tweet",
            "_id": "1",
            "_version": 4,
            "found": true,
            "_source": {
                "user": "kimchy",
                "postDate": "2017-08-20T12:44:42.614Z",
                "message": "trying out Elasticsearch"
            }
        }
         */
    }

    @Test
    public void deleteTest() {
        DeleteResponse deleteResponse = esService.delete(client);
        System.out.println(deleteResponse);
        /**
         DeleteResponse[
            index=twitter,
            type=tweet,
            id=1,
            version=6,
            result=deleted,
            shards=ShardInfo{
                total=2,
                successful=1,
                failures=[

                ]
            }
        ]

         */
    }

    @Test
    public void deleteByQueryTest() {
        BulkByScrollResponse bulkByScrollResponse = esService.deleteByQuery(client);
        System.out.println(bulkByScrollResponse);
//        long deleted = bulkByScrollResponse.getDeleted();
        /**
         BulkIndexByScrollResponse[
         took=153.4ms,
         timed_out=false,
         sliceId=null,
         updated=0,
         created=0,
         deleted=1,
         batches=1,
         versionConflicts=0,
         noops=0,
         retries=0,
         throttledUntil=0s,
         bulk_failures=[

         ],
         search_failures=[

         ]
         ]
         */
    }

    @Test
    public void deleteByQueryAsyncTest() {
        esService.deleteByQueryAsync(client);
    }

    @Test
    public void updateTest() {
        UpdateResponse updateResponse = esService.update(client);
        System.out.println(updateResponse);
    }

    @Test
    public void bulkTest() {
        esService.bulk(client);
    }

    @Test
    public void bucketAggregationsTest() {
        try {
            esService.bucketAggregations(client);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
