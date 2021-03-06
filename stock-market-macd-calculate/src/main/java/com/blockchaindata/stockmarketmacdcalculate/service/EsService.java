package com.blockchaindata.stockmarketmacdcalculate.service;

import com.alibaba.fastjson.JSONObject;
import com.blockchaindata.stockmarketcommon.domain.BaseModel;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import io.searchbox.indices.settings.GetSettings;
import io.searchbox.indices.settings.UpdateSettings;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Andon
 * 2021/01/27
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

    /**
     * 创建索引index
     */
    public void createIndex(String indexName) {
        try {
            JestResult jestResult = jestClient.execute(new CreateIndex.Builder(indexName).build());
            log.info("createIndex indexName:{} state:{}", indexName, jestResult.isSucceeded());
        } catch (IOException e) {
            log.error("createIndex failure!! indexName:{} error={}", indexName, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 删除索引index
     */
    public void deleteIndex(String indexName) {
        try {
            JestResult result = jestClient.execute(new DeleteIndex.Builder(indexName).build());
            log.info("deleteIndex indexName:{} state:{}", indexName, result.isSucceeded());
        } catch (Exception e) {
            log.error("deleteIndex failure!! indexName:{} error={}", indexName, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建索引index的映射mapping
     *
     * @param mapping json格式的映射mappings
     */
    public void createIndexMappings(String indexName, String typeName, String mapping) {
        try {
            JestResult result;
            if (ObjectUtils.isEmpty(typeName)) {
                result = jestClient.execute(new PutMapping.Builder(indexName, indexName, mapping).build());
            } else {
                result = jestClient.execute(new PutMapping.Builder(indexName, typeName, mapping).build());
            }
            log.info("createIndexMappings indexName:{} typeName:{} mapping:{} state:{}", indexName, typeName, mapping, result.isSucceeded());
        } catch (Exception e) {
            log.error("createIndexMappings failure!! indexName:{} typeName:{} mapping:{} error{}", indexName, typeName, mapping, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取索引index的映射mappings
     */
    public String getIndexMappings(String indexName, String typeName) {
        try {
            JestResult result;
            if (ObjectUtils.isEmpty(typeName)) {
                result = jestClient.execute(new GetMapping.Builder().addIndex(indexName).build());
            } else {
                result = jestClient.execute(new GetMapping.Builder().addIndex(indexName).addType(typeName).build());
            }
            log.info("getIndexMappings indexName:{} typeName:{} state:{}", indexName, typeName, result.isSucceeded());
            String jsonString = result.getJsonString();
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            return jsonObject.getJSONObject(indexName).getJSONObject("mappings").toJSONString();
        } catch (IOException e) {
            log.error("getIndexMappings failure!! indexName:{} typeName:{} error={}", indexName, typeName, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取索引index的设置settings
     */
    public String getIndexSettings(String indexName) {
        try {
            JestResult result = jestClient.execute(new GetSettings.Builder().addIndex(indexName).build());
            log.info("getIndexSettings indexName:{} state:{}", indexName, result.isSucceeded());
            String jsonString = result.getJsonString();
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            return jsonObject.getJSONObject(indexName).getJSONObject("settings").toJSONString();
        } catch (IOException e) {
            log.error("getIndexSettings failure!! indexName:{} error={}", indexName, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改索引index的设置settings
     *
     * @param settings json格式的设置settings(传值示例：{"max_result_window":"200000000"})
     */
    public void updateIndexSettings(String indexName, String settings) {
        try {
            JestResult jestResult = jestClient.execute(new UpdateSettings.Builder(settings).addIndex(indexName).build());
            log.info("updateIndexSettings indexName:{} settings:{} state:{}", indexName, settings, jestResult.isSucceeded());
        } catch (Exception e) {
            log.error("updateIndexSettings failure!! indexName:{} settings:{} error={}", indexName, settings, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * SearchSourceBuilder进行查询
     */
    public SearchResult searchSource(String indexName, String typeName, SearchSourceBuilder searchSourceBuilder) {
        try {
            String searchSourceBuilderStr = searchSourceBuilder.toString();
            if (ObjectUtils.isEmpty(typeName)) {
                Search search = new Search.Builder(searchSourceBuilderStr).addIndex(indexName).build();
                return jestClient.execute(search);
            } else {
                Search search = new Search.Builder(searchSourceBuilderStr).addIndex(indexName).addType(typeName).build();
                return jestClient.execute(search);
            }
        } catch (IOException e) {
            log.error("searchSource failure!! indexName:{} typeName:{} error={}", indexName, typeName, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送json查询
     */
    SearchResult jsonSearch(String indexName, String typeName, String json) {
        try {
            if (ObjectUtils.isEmpty(typeName)) {
                return jestClient.execute(new Search.Builder(json).addIndex(indexName).build());
            } else {
                return jestClient.execute(new Search.Builder(json).addIndex(indexName).addType(typeName).build());
            }
        } catch (Exception e) {
            log.error("jsonSearch failure!! indexName:{} typeName:{} json:{} error={}", indexName, typeName, json, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 批量写入
     */
    public <T extends BaseModel> void bulkIndex(String indexName, Collection<T> collection) {
        try {
            Bulk.Builder bulk = new Bulk.Builder();
            for (T o : collection) {
                Index index;
                if (ObjectUtils.isEmpty(o.getType())) {
                    index = new Index.Builder(o).id(o.getPK()).index(indexName).type(indexName).build();
                } else {
                    index = new Index.Builder(o).id(o.getPK()).index(indexName).type(o.getType()).build();
                }
                bulk.addAction(index);
            }
            jestClient.execute(bulk.build());
            log.info("bulkIndex >> indexName:{} collection.size={}", indexName, collection.size());
        } catch (IOException e) {
            log.error("bulkIndex failure!! indexName:{} error:{} ", indexName, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 新增或者更新文档
     */
    public <T> void insertOrUpdateDocumentById(String indexName, String typeName, String id, T o) {
        try {
            Index.Builder builder = new Index.Builder(o).id(id).refresh(true);
            Index index;
            if (ObjectUtils.isEmpty(typeName)) {
                index = builder.index(indexName).type(indexName).build();
            } else {
                index = builder.index(indexName).type(typeName).build();
            }
            jestClient.execute(index);
        } catch (IOException e) {
            log.error("insertOrUpdateDocumentById failure!! indexName:{} typeName:{} id={} error={}", indexName, typeName, id, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 根据主键id删除文档
     */
    public void deleteDocumentById(String indexName, String typeName, String id) {
        try {
            if (ObjectUtils.isEmpty(typeName)) {
                jestClient.execute(new Delete.Builder(id).index(indexName).build());
            } else {
                jestClient.execute(new Delete.Builder(id).index(indexName).type(typeName).build());
            }
        } catch (IOException e) {
            log.error("deleteDocumentById failure!! indexName:{} typeName:{} id={} error={}", indexName, typeName, id, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 根据主键id获取文档
     */
    public <T> T getDocumentById(String indexName, String id, T object) {
        try {
            JestResult result = jestClient.execute(new Get.Builder(indexName, id).build());
            return (T) result.getSourceAsObject(object.getClass(), false);
        } catch (IOException e) {
            log.error("getDocumentById failure!! indexName:{} id={} error={}", indexName, id, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
