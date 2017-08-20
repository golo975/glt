package com.gaolong.tageverything.service.es;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.reindex.BulkByScrollResponse;

public interface EsService {
    IndexResponse index(TransportClient client);

    GetResponse getDocumentById(TransportClient client);

    DeleteResponse delete(TransportClient client);

    BulkByScrollResponse deleteByQuery(TransportClient client);

    void deleteByQueryAsync(TransportClient client);

    UpdateResponse update(TransportClient client);
}
