package de.larmic.testing

import de.larmic.testcontainers.elasticsearch.TweetDocument
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest
import org.elasticsearch.action.admin.indices.get.GetIndexRequest
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient

fun RestHighLevelClient.refreshIndex() : RestHighLevelClient {
    val request = RefreshRequest(TweetDocument.documentIndex)
    this.indices().refresh(request, RequestOptions.DEFAULT)
    return this
}

fun RestHighLevelClient.deleteIndexIfExists() : RestHighLevelClient {
    if (this.indexExists(TweetDocument.documentIndex, TweetDocument.documentType)) {
        this.indices().delete(DeleteIndexRequest(TweetDocument.documentIndex), RequestOptions.DEFAULT)
    }
    return this
}

fun RestHighLevelClient.createIndex(): RestHighLevelClient {
    val createIndexRequest = CreateIndexRequest(TweetDocument.documentIndex)
    this.indices().create(createIndexRequest, RequestOptions.DEFAULT)
    return this
}

private fun RestHighLevelClient.indexExists(index: String, type: String) = this.indices().exists(createGetIndexRequest(index, type), RequestOptions.DEFAULT)

private fun createGetIndexRequest(index: String, type: String): GetIndexRequest {
    val getIndexRequest = GetIndexRequest()
    getIndexRequest.indices(index)
    getIndexRequest.types(type)
    return getIndexRequest
}