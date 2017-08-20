package com.gaolong.tageverything.service.es;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-update.html
 */
@Service
public class EsServiceImpl implements EsService {

    Log logger = LogFactory.getLog(EsServiceImpl.class);

    public IndexResponse index(TransportClient client) {
        try {
            XContentBuilder sourceBuilder = jsonBuilder()
                    .startObject()
                    .field("user", "kimchy")
                    .field("postDate", new Date())
                    .field("message", "trying out Elasticsearch")
//                    .field("gender", "female")
                    .endObject();

            // IndexRequestBuilder 用于向ES中插入数据
            IndexRequestBuilder indexRequestBuilder = client
                    .prepareIndex("twitter", "tweet", "1")// 根据 index, type, id 3个参数来插入数据
//                    .prepareIndex("persons", "tweet", "2")// 根据 index, type, id 3个参数来插入数据
                    .setSource(sourceBuilder);// setSource() 方法用于插入数据

            // IndexResponse 是 index 方法的返回值
            IndexResponse indexResponse = indexRequestBuilder.get();// get()方法的作用是执行然后获取结果
            return indexResponse;// index 操作的返回值是 IndexResponse
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GetResponse getDocumentById(TransportClient client) {
        GetRequestBuilder getRequestBuilder = client.prepareGet("twitter", "tweet", "1")// 根据 index, type, id 3个参数来查找数据
//        GetRequestBuilder getRequestBuilder = client.prepareGet("persons", "tweet", "1")// 根据 index, type, id 3个参数来查找数据
                .setOperationThreaded(false);//true:执行线程与调用线程不同；false:执行线程与调用线程相同。默认为true，即不同。
        GetResponse response = getRequestBuilder.get();
        return response;// get() 方法的返回值是 GetResponse
    }

    @Override
    public DeleteResponse delete(TransportClient client) {
        DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete("twitter", "tweet", "1");// 根据 index, type, id 3个参数来删除数据
        DeleteResponse response = deleteRequestBuilder.get();
        return response;
    }

    @Override
    public BulkByScrollResponse deleteByQuery(TransportClient client) {
        DeleteByQueryAction deleteByQueryAction = DeleteByQueryAction.INSTANCE;

        DeleteByQueryRequestBuilder deleteByQueryRequestBuilder = deleteByQueryAction.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("gender", "male"))// 查询条件是gender==male
                .source("persons");//所查询的index

        // 如果index（即上面的persons）不存在，执行时会报错
        BulkByScrollResponse bulkByScrollResponse = deleteByQueryRequestBuilder.get();
        long deleted = bulkByScrollResponse.getDeleted();//删除的数据量
        return bulkByScrollResponse;
    }

    /**
     * 异步+回调的方式执行deleteByQuery
     *
     * @param client
     */
    @Override
    public void deleteByQueryAsync(TransportClient client) {
        DeleteByQueryAction deleteByQueryAction = DeleteByQueryAction.INSTANCE;

        DeleteByQueryRequestBuilder deleteByQueryRequestBuilder = deleteByQueryAction.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("gender", "male"))
                .source("persons");

        // TODO: 2017/8/21 下面两个回调方法的日志怎么打印出来？现在应该还没有正确配置日志组件。
        deleteByQueryRequestBuilder.execute(new ActionListener<BulkByScrollResponse>() {
            @Override
            public void onResponse(BulkByScrollResponse response) {
                long deleted = response.getDeleted();
                System.out.println("deleted:" + deleted);
                logger.info(deleted);
            }

            @Override
            public void onFailure(Exception e) {
                // Handle the exception
                logger.info("Failure", e);
                System.out.println("Failure");
            }
        });
    }

    @Override
    public UpdateResponse update(TransportClient client) {
        try {
            UpdateRequest updateRequest = new UpdateRequest()
                    // 经验证，如果原对象根据（index, type, id）查找不存在，则更新操作会报错
                    .index("persons").type("tweet").id("2")
                    .doc(jsonBuilder()
                            .startObject()
                            .field("gender", "male")
                            .endObject());

            UpdateResponse updateResponse = client.update(updateRequest).get();
            return updateResponse;
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
