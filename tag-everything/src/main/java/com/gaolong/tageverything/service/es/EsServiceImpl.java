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
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filters.Filters;
import org.elasticsearch.search.aggregations.bucket.filters.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filters.FiltersAggregator;
import org.elasticsearch.search.aggregations.bucket.global.Global;
import org.elasticsearch.search.aggregations.bucket.global.GlobalAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.missing.Missing;
import org.elasticsearch.search.aggregations.bucket.missing.MissingAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCount;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search-msearch.html
 */
@Service
public class EsServiceImpl implements EsService {

    private Log logger = LogFactory.getLog(EsServiceImpl.class);

    public IndexResponse index(TransportClient client) {
        try {
            XContentBuilder sourceBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("user", "kimchy")
                    .field("postDate", new Date())
                    .field("message", "trying out Elasticsearch")
                    .endObject();

            // IndexRequestBuilder 用于向ES中插入数据
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex("twitter", "tweet", "1")
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
        GetRequestBuilder getRequestBuilder = client.prepareGet("twitter", "tweet", "1")
                .setOperationThreaded(false);//true:执行线程与调用线程不同；false:执行线程与调用线程相同。默认为true，即不同。
        GetResponse response = getRequestBuilder.get();
        return response;// get() 方法的返回值是 GetResponse
    }

    @Override
    public DeleteResponse delete(TransportClient client) {
        DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete("twitter", "tweet", "1");
        DeleteResponse response = deleteRequestBuilder.get();
        return response;
    }

    // TODO: 2017/8/27 这个方法还没有弄明白
    @Override
    public BulkByScrollResponse deleteByQuery(TransportClient client) {
        DeleteByQueryAction deleteByQueryAction = DeleteByQueryAction.INSTANCE;

        DeleteByQueryRequestBuilder deleteByQueryRequestBuilder = deleteByQueryAction.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("gender", "male"))// 查询条件是gender==male
                .source("persons");//所查询的index

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
                    .doc(XContentFactory.jsonBuilder().startObject().field("gender", "male").endObject());

            // TODO: 2017/8/27 直接使用update()和使用prepareUpdate() 有什么区别？
            UpdateResponse updateResponse = client.update(updateRequest).get();
            return updateResponse;
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新或插入
     *
     * @param client
     */
    @Override
    public UpdateResponse upsert(TransportClient client) {
        try {
            // 1. 如果指定的Document不存在，则插入indexRequest中的内容
            IndexRequest indexRequest = new IndexRequest("index", "type", "1")
                    .source(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("name", "Joe Smith")
                            .field("gender", "male")
                            .endObject());

            UpdateRequest updateRequest = new UpdateRequest("index", "type", "1")
                    // 2. 如果指定的Document已经存在，就根据doc()方法指定的数据进行更新
                    .doc(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("gender", "male")
                            .endObject())
                    .upsert(indexRequest);

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
                    .doc(XContentFactory.jsonBuilder().startObject().field("gender", "male").endObject());
            client.update(updateRequest).get();
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
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
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject())
            );

            bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "another post")
                            .endObject())
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
                .source(XContentFactory.jsonBuilder().startObject().endObject()));
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
                .setQuery(
                        QueryBuilders.termQuery("multi", "test"))
                .setPostFilter(
                        QueryBuilders
                                .rangeQuery("age")
                                .from(12)
                                .to(18))
                .setFrom(0)
                .setSize(60)
                .setExplain(true)
                .get();

    }

    /**
     * Scroll api ， 类似传统关系型数据库中提供的cursor，用于分页，即将大量的数据分批次返回。
     * 第一次插入，需要传入查询条件，以后的插入就不需要了。
     * 每次查询之后都会生成一个 scroll_id，用于下次查询时作为入参。只有最近一次查询的 scroll_id 时有效的。
     * 指定size，以确定每次返回的数据量。
     * scroll参数用于指定每次查询会话的存活时间。每次查询后，此时间会更新。即，每次查询都有自己的会话有效时间。
     * 如果查询中有聚合，则聚合的结果只在第一次查询的返回值中。
     * 如果对查询结果的顺序不要求的话，指定sort=_doc可以获得最好的执行效率
     * 第一次查询成为search查询，之后的查询成为scroll查询。
     * <p>
     * 在使用ES的过程中，因为会删除数据，这会产生一些小的segment，ES会持续不断地将比较小的segment进行合并，以提高效率，这类似于"碎片整理"。
     * 在执行scroll的过程中，这个整理过程依然在进行。为了保证数据的准确，每次查询对应的 search context 会保留那些已经被删除但依然在此次查询中有用的数据，
     * 这保证了在scroll请求中得到的结果就是第一次进行search请求时的预期结果。
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
                    .setScroll(new TimeValue(60_000))
                    .execute()
                    .actionGet();
        }
        while (scrollResp.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.
    }

    public void multiSearch(TransportClient client) {
        SearchRequestBuilder srb1 = client.prepareSearch().setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(1);
        SearchRequestBuilder srb2 = client.prepareSearch().setQuery(QueryBuilders.matchQuery("name", "kimchy")).setSize(1);

        MultiSearchResponse sr = client.prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .get();

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().getTotalHits();
        }
    }

    // **************  下面是 Search 的 using Aggregations 部分 ****************

    public void aggregation(TransportClient client) {
        SearchResponse searchResponse = client.prepareSearch()
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(AggregationBuilders.terms("agg1").field("field"))
                .addAggregation(AggregationBuilders
                        .dateHistogram("agg2")
                        .field("birth")
                        .dateHistogramInterval(DateHistogramInterval.YEAR))
                .get();

        // Get your facet results
        Terms agg1 = searchResponse.getAggregations().get("agg1");
        Histogram agg2 = searchResponse.getAggregations().get("agg2");
    }

    /**
     * 限制【每个分片(shard)】返回的Document的数量
     *
     * @param client
     */
    public void terminateAfter(TransportClient client) {
        SearchResponse searchResponse = client.prepareSearch("index")
                .setTerminateAfter(1000)
                .get();
        if (searchResponse.isTerminatedEarly()) {// 判断返回的结果是否收到了上面设置的TerminateAfter的限制
            // We finished early..
        }
    }

    public void searchTemplate(TransportClient client) {
        // 暂略： https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search-template.html
    }

    public void aggregations(TransportClient client) {
        QueryBuilder queryBuilder = QueryBuilders.termQuery("", "");// TODO: 2017/8/23 ?????? 这句是我自己写的。。。
        SearchResponse searchResponse = client.prepareSearch()
                .setQuery(queryBuilder)
                .addAggregation(AggregationBuilders.count("name"))
                .execute().actionGet();

    }

    // **************  下面是 Aggregations 部分 ****************

    public void structuringAggregations(TransportClient client) {
        SearchResponse sr = client.prepareSearch()
                .addAggregation(AggregationBuilders
                        .terms("by_country")// terms 是聚合的类型；"by_country"是聚合的名字
                        .field("country")// 这里的 field 是 aggregation_body 部分，不同的聚合类型会有不同的 aggregation_body 。

                        .subAggregation(AggregationBuilders // 在上一级聚合结果的基础上进行的【子聚合】
                                .dateHistogram("by_year") // DateHistogram / Histogram 和 Terms 一样，也是 Bucketing 类型的聚合。
                                .field("dateOfBirth") // DateHistogram / Histogram 需要指定 field 和 interval 2个参数。
                                .dateHistogramInterval(DateHistogramInterval.YEAR)

                                .subAggregation(AggregationBuilders
                                        .avg("avg_children")// avg 是 Metric 类型的聚合
                                        .field("children"))
                        )
                ).execute().actionGet();
    }

    /**
     * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/_metrics_aggregations.html
     *
     * @param client
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void metricsAggregations(TransportClient client) throws ExecutionException, InterruptedException {
        {
            MinAggregationBuilder aggregation = AggregationBuilders.min("agg").field("height");
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Min agg = searchResponse.getAggregations().get("agg");
            double value = agg.getValue();
        }
        {
            MaxAggregationBuilder aggregation = AggregationBuilders.max("agg").field("height");
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Max agg = searchResponse.getAggregations().get("agg");
            double value = agg.getValue();
        }
        {
            SumAggregationBuilder aggregation = AggregationBuilders.sum("agg").field("height");
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Sum agg = searchResponse.getAggregations().get("agg");
            double value = agg.getValue();
        }
        {
            AvgAggregationBuilder aggregation = AggregationBuilders.avg("agg").field("height");
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Avg avg = searchResponse.getAggregations().get("agg");
            double value = avg.getValue();
        }
        {
            ValueCountAggregationBuilder aggregation = AggregationBuilders.count("agg").field("height");// 注意这里的方法名是count，不是valueCount
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            ValueCount agg = searchResponse.getAggregations().get("agg");
            long value = agg.getValue();
        }
        {
            StatsAggregationBuilder aggregation = AggregationBuilders.stats("agg").field("height");
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Stats agg = searchResponse.getAggregations().get("agg");
            double min = agg.getMin();
            double max = agg.getMax();
            double avg = agg.getAvg();
            double sum = agg.getSum();
            long count = agg.getCount();
        }
        {
            ExtendedStatsAggregationBuilder aggregation = AggregationBuilders.extendedStats("agg").field("height");
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            ExtendedStats agg = searchResponse.getAggregations().get("agg");
            double min = agg.getMin();
            double max = agg.getMax();
            double avg = agg.getAvg();
            double sum = agg.getSum();
            long count = agg.getCount();
            double stdDeviation = agg.getStdDeviation();// 标准差
            double sumOfSquares = agg.getSumOfSquares();// 平方和
            double variance = agg.getVariance();// 方差
        }
        {
            /* 略：Percentile AggregationBuilders */
        }
        {
            /* 略：Percentile Ranks Aggregation */
        }
        {
            /* 略：Cardinality Aggregation */
        }
        {
            /* 略：Geo Bounds Aggregation */
        }
        {
            /* 略：Top Hits Aggregation */
        }
        {
            /* 略：Scripted Metric Aggregation : 使用 groovy 脚本*/
        }

    }

    /**
     * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/_bucket_aggregations.html
     *
     * @param client
     */
    @Override
    public void bucketAggregations(TransportClient client) throws ExecutionException, InterruptedException {
        {
            /*
             * 1. Global Aggregation 的 search execution context 是全部的index和type，而且这不会受到query的影响，
             * 也就是说即使query中指定了要查询的index或type，Global Aggregation的执行上下文仍然是全部的数据。
             * 2. 因为上面说明的内容，Global Aggregation只能作为最顶层的Aggregation，否则没有意义。
             * 3. Global Aggregation 是一个 Bucketing 类型的Aggregation，只不过只有一个bucket，即全体。
             */
            GlobalAggregationBuilder aggregation = AggregationBuilders.global("agg")
                    .subAggregation(AggregationBuilders.terms("genders").field("gender"));
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Global agg = searchResponse.getAggregations().get("agg");
            System.out.println(agg.getDocCount());
        }
        {
            /*
             * Filter Aggregation
             * 1. 定义一个 single bucket，其中包含【当前上下文中】【符合条件的】所有的Document。
             * 2. 作用顾名思义，是为了对当前上下文中是数据进行过滤（filter）。
             */
            // TODO: 2017/8/26 QueryBuilders.termQuery() 是什么意思？？
            FilterAggregationBuilder aggregation = AggregationBuilders.filter("agg", QueryBuilders.termQuery("gender", "male"));
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Filter agg = searchResponse.getAggregations().get("agg");
            agg.getDocCount();
        }
        {
            /*
             * Filters Aggregation
             * 1. Defines a multi bucket Aggregation where each bucket is associated with a filter.
             * 2. Each bucket will collect all documents that match its associated filter.
             */
            FiltersAggregationBuilder aggregation = AggregationBuilders.filters("agg",
                    new FiltersAggregator.KeyedFilter("men", QueryBuilders.termQuery("gender", "male")),
                    new FiltersAggregator.KeyedFilter("women", QueryBuilders.termQuery("gender", "female")));
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Filters agg = searchResponse.getAggregations().get("agg");
            for (Filters.Bucket bucket : agg.getBuckets()) {
                String key = bucket.getKeyAsString();
                long docCount = bucket.getDocCount();
            }
        }
        {
            /*
             * Missing Aggregation
             * 缺少某个字段的所有Document
             */
            MissingAggregationBuilder aggregation = AggregationBuilders.missing("agg").field("gender");
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Missing agg = searchResponse.getAggregations().get("agg");
            agg.getDocCount();
        }
        {
            /*
             * Nested Aggregation
             * A special single bucket aggregation that enables aggregating nested documents.
             * 嵌套聚合。通常的聚合是在Document级别或者Document中的field级别对Document进行聚合，嵌套聚合是在Document的Field的Field级别上进行聚合操作。
             * 例如在【订单】的Document中，每个订单包含多个商品，每个商品包含自己的购买数量和单价等信息，
             * 现在要查询一个人的所有的订单中，单价最贵的商品所在的订单，即【订单->商品->价格】3层。
             */
            NestedAggregationBuilder aggregation = AggregationBuilders.nested("agg", "goods");
            SearchResponse searchResponse = client.prepareSearch().addAggregation(aggregation).execute().get();
            Nested agg = searchResponse.getAggregations().get("agg");
            agg.getDocCount();
        }
        {
            /**
             * Reverse Nested Aggregation
             * A special single bucket aggregation that enables aggregating on parent docs from nested documents.
             * The reverse_nested aggregation must be defined inside a nested aggregation.
             */
            // 暂略。。https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/_bucket_aggregations.html
        }
        {
            /**
             * Children Aggregation
             * Terms Aggregation
             * Order
             * Significant Terms Aggregation
             * Range Aggregation
             * Date Range Aggregation
             * Ip Range Aggregation
             * Histogram Aggregation
             * Date Histogram Aggregation
             * Geo Distance Aggregation
             * Geo Hash Grid Aggregation
             */
        }
    }

    // **************  下面是 Query DSL 部分 ****************

    /*
     * Elasticsearch provides a full Java query dsl in a similar manner to the REST Query DSL.
     * The factory for query builders is QueryBuilders.
     * Once your query is ready, you can use the Search API :
     * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search.html
     * <p>
     * You can easily print JSON generated queries using toString() method on QueryBuilder object.
     * The QueryBuilder can then be used with any API that accepts a query, such as count and search.
     */

    /**
     * Match All Query
     * 最简单的查询，所有的Document都符合条件。所有Document的 _score（即相关度） 都是 1.0
     * 与之对应的还有 Match None Query ，即所有的Document都不匹配。
     * Match All Query 和 Match None Query 正好是相反的。
     *
     * @param client
     */
    public void matchAllQuery(TransportClient client) {
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
    }

    /**
     * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-full-text-queries.html
     * 暂略。。。。。
     *
     * @param client
     */
    public void fullTextQueries(TransportClient client) {

    }


}
