package com.blockchaindata.stockmarketmacdcalculate.service;

import com.blockchaindata.stockmarketcommon.domain.BaseModel;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Andon
 * 2020/9/24
 */
@Slf4j
@Service
public class EsService {

    private JestClient jestClient;
    @Value("${elasticsearch.uris.http}")
    private String url;

    @PostConstruct
    public void init() {
        List<String> uris = Arrays.asList(url.split(","));
        JestClientFactory jestClientFactory = new JestClientFactory();
        jestClientFactory.setHttpClientConfig(new HttpClientConfig
                .Builder(uris)
                .connTimeout(10000)
                .readTimeout(10000)
                .maxConnectionIdleTime(1500L, TimeUnit.MILLISECONDS)
                .multiThreaded(true)
                .build());
        jestClient = jestClientFactory.getObject();
    }

    public SearchResult searchSource(SearchSourceBuilder searchSourceBuilder, String index, String type) {
        try {
            String searchSourceBuilderStr = searchSourceBuilder.toString();
            if (ObjectUtils.isEmpty(type)) {
                Search search = new Search.Builder(searchSourceBuilderStr).addIndex(index).build();
                return jestClient.execute(search);
            } else {
                Search search = new Search.Builder(searchSourceBuilderStr).addIndex(index).addType(type).build();
                return jestClient.execute(search);
            }
        } catch (IOException e) {
            log.error("searchSource failure!! error={}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送json查询
     */
    SearchResult jsonSearch(String json, String indexName, String typeName) {
        Search search = new Search.Builder(json).addIndex(indexName).addType(typeName).build();
        try {
            return jestClient.execute(search);
        } catch (Exception e) {
            log.error("index:{}, type:{}, search again!! error={}", indexName, typeName, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 批量写入
     */
    <T extends BaseModel> void bulkIndex(List<T> list, String indexName) {
        Bulk.Builder bulk = new Bulk.Builder();
        for (T o : list) {
            Index index = new Index.Builder(o).id(o.getPK()).index(indexName).type(o.getType()).build();
            bulk.addAction(index);
        }
        try {
            jestClient.execute(bulk.build());
            log.info("bulkIndex >> indexName={} list.size={}", indexName, list.size());
        } catch (IOException e) {
            log.error("bulkIndex failure!! error={} index={}", e.getMessage(), indexName);
            e.printStackTrace();
        }
    }

    /**
     * 新增或者更新文档
     */
    public <T> void insertOrUpdateDocumentById(T o, String index, String type, String uniqueId) {
        Index.Builder builder = new Index.Builder(o);
        builder.id(uniqueId);
        builder.refresh(true);
        Index indexDoc = builder.index(index).type(type).build();
        try {
            jestClient.execute(indexDoc);
        } catch (IOException e) {
            log.error("insertOrUpdateDocumentById failure!! error={} id={}", e.getMessage(), uniqueId);
            e.printStackTrace();
        }
    }

    /**
     * 根据主键id删除文档
     */
    public void deleteDocumentById(String index, String type, String id) {
        Delete delete = new Delete.Builder(id).index(index).type(type).build();
        try {
            jestClient.execute(delete);
        } catch (IOException e) {
            log.error("deleteDocumentById failure!! error={} id={}", e.getMessage(), id);
            e.printStackTrace();
        }
    }

    /**
     * 根据主键id获取文档
     */
    public <T> T getDocumentById(T object, String index, String id) {
        Get get = new Get.Builder(index, id).build();
        T o = null;
        try {
            JestResult result = jestClient.execute(get);
            o = (T) result.getSourceAsObject(object.getClass());
        } catch (IOException e) {
            log.warn("getDocumentById again!! error={} id={}", e.getMessage(), id);
            e.printStackTrace();
            getDocumentById(object, index, id);
        }
        return o;
    }

    public SearchResult jsonSearchNoType(String json, String indexName) {
        Search search = new Search.Builder(json).addIndex(indexName).build();
        try {
            return jestClient.execute(search);
        } catch (Exception e) {
            log.warn("index:{}, jsonSearchNoType failure!! error={}", indexName, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
