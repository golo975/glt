package com.gaolong.tageverything.service.es;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search-msearch.html
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
                    .doc(jsonBuilder().startObject().field("gender", "male").endObject());

            UpdateResponse updateResponse = client.update(updateRequest).get();
            return updateResponse;
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void prepareUpdateByScript(TransportClient client) {
        UpdateRequest updateRequest = new UpdateRequest("ttl", "doc", "1")
                .script(new Script("ctx._source.gender = \"male\""));
        try {
            client.update(updateRequest).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepareUpdateByMergingDocument(TransportClient client) {
        try {
            UpdateRequest updateRequest = new UpdateRequest("index", "type", "1")
                    .doc(jsonBuilder()
                            .startObject()
                            .field("gender", "male")
                            .endObject());

            client.update(updateRequest).get();
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新或插入
     *
     * @param client
     */
    @Override
    public UpdateResponse upsert(TransportClient client) {
        try {
            IndexRequest indexRequest = new IndexRequest("index", "type", "1")
                    .source(jsonBuilder()
                            .startObject()
                            .field("name", "Joe Smith")
                            .field("gender", "male")
                            .endObject());

            UpdateRequest updateRequest = new UpdateRequest("index", "type", "1")
                    .doc(jsonBuilder()// 1. 如果指定的Document已经存在，就根据doc()方法指定的数据进行更新
                            .startObject()
                            .field("gender", "male")
                            .endObject())
                    .upsert(indexRequest);// 2. 如果指定的Document不存在，则插入indexRequest中的内容

            UpdateResponse updateResponse = client.update(updateRequest).get();
            return updateResponse;
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void multiGet(TransportClient client) {
        MultiGetResponse multiGetResponse = client.prepareMultiGet()
                .add("twitter", "tweet", "1")// 1. 根据 (index, type, id) 获取指定数据
                .add("twitter", "tweet", "2", "3", "4")// 2. 在相同的 (index, type) 中根据多个id获取数据
                .add("another", "type", "foo")// 3. 可以同时从不同的 index 中获取数据
                .get();

        for (MultiGetItemResponse itemResponse : multiGetResponse) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
                System.out.println(json);
            }
        }
    }

    @Override
    public void bulk(TransportClient client) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();

// either use client#prepare, or use Requests# to directly build index/delete requests
        try {
            bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
            );

            bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "another post")
                            .endObject()
                    )
            );

            BulkResponse bulkResponse = bulkRequest.get();
            if (bulkResponse.hasFailures()) {
                // process failures by iterating through each bulk response item
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void bulkProcess(TransportClient client) throws IOException, InterruptedException {
        BulkProcessor bulkProcessor = BulkProcessor.builder(client,
                new BulkProcessor.Listener() {
                    /**
                     * 批量执行前调用
                     * @param executionId
                     * @param request
                     */
                    @Override
                    public void beforeBulk(long executionId, BulkRequest request) {
                        System.out.println("beforeBulk");
                    }

                    /**
                     * 批量执行结束后调用
                     * @param executionId
                     * @param request
                     * @param response
                     */
                    @Override
                    public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                        boolean has = response.hasFailures();//是否有执行失败的请求
                        System.out.println(has);
                    }

                    /**
                     * 批量执行失败，抛出Throwable时调用
                     * @param executionId
                     * @param request
                     * @param failure
                     */
                    @Override
                    public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                        System.out.println("afterBulk");
                    }
                })
                .setBulkActions(10000)// todo gaolong 每次批量执行的数量？还是每次到此数量之后才会触发批量执行？
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))// todo gaolong 每当数据量到达5M时刷新一次数据
                .setFlushInterval(TimeValue.timeValueSeconds(5))// todo gaolong 无论请求量有多少，每5秒刷新一次数据
                .setConcurrentRequests(1)// todo gaolong 并发请求数量？？
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();

        bulkProcessor.add(new IndexRequest("twitter", "tweet", "1")
                .source(jsonBuilder().startObject().endObject()));
        bulkProcessor.add(new DeleteRequest("twitter", "tweet", "2"));

        bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
//        bulkProcessor.close(); // 上面一句和这一句都是close。

    }

    //  以上是 Document APIs ，下面开始Search API

    public void search(TransportClient client) {
        client.prepareSearch().get();// 这是 search 的最简形式

        client.prepareSearch("index1", "index2")
                .setTypes("type1", "type2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(termQuery("multi", "test"))
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
                .setFrom(0).setSize(60).setExplain(true)
                .get();

    }

    /*
    Scroll api ， 类似传统关系型数据库中提供的cursor，用于分页，即将大量的数据分批次返回。
    第一次插入，需要传入查询条件，以后的插入就不需要了。
    每次查询之后都会生成一个 scroll_id，用于下次查询时作为入参。只有最近一次查询的 scroll_id 时有效的。
    指定size，以确定每次返回的数据量。
    scroll参数用于指定每次查询会话的存活时间。每次查询后，此时间会更新。即，每次查询都有自己的会话有效时间。
    如果查询中有聚合，则聚合的结果只在第一次查询的返回值中。
    如果对查询结果的顺序不要求的话，指定sort=_doc可以获得最好的执行效率
    第一次查询成为search查询，之后的查询成为scroll查询。

    在使用ES的过程中，因为会删除数据，这会产生一些小的segment，ES会持续不断地将比较小的segment进行合并，以提高效率，这类似于"碎片整理"。
    在执行scroll的过程中，这个整理过程依然在进行。为了保证数据的准确，每次查询对应的 search context 会保留那些已经被删除但依然在此次查询中有用的数据，
    这保证了在scroll请求中得到的结果就是第一次进行search请求时的预期结果。

     */

    public void scroll(TransportClient client) {
        QueryBuilder qb = QueryBuilders.termQuery("multi", "test");

        SearchResponse scrollResp = client.prepareSearch("index")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(new TimeValue(60000))
                .setQuery(qb)
                .setSize(100)//max of 100 hits will be returned for each scroll
                .get();

        //Scroll until no hits are returned
        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                //Handle the hit...
            }
            scrollResp = client
                    .prepareSearchScroll(scrollResp.getScrollId())
                    .setScroll(new TimeValue(60000))
                    .execute()
                    .actionGet();
        }
        while (scrollResp.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.
    }

}
