package com.blockchaindata.stockmarketmacdcalculate.service;

import com.blockchaindata.stockmarketcommon.domain.BaseModel;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Andon
 * @date 2019/2/21
 */
@Service
public class EsService {

    private static final Logger LOG = LoggerFactory.getLogger(EsService.class);

    @Resource
    private JestClient jestClient;

    /**
     * 发送json查询
     */
    SearchResult jsonSearch(String json, String indexName, String typeName) {
        Search search = new Search.Builder(json).addIndex(indexName).addType(typeName).build();
        try {
            return jestClient.execute(search);
        } catch (Exception e) {
            LOG.warn("index:{}, type:{}, search again!! error = {}", indexName, typeName, e.getMessage());
            sleep(100);
            return jsonSearch(json, indexName, typeName);
        }
    }

    /**
     * 批量写入
     */
    public <T extends BaseModel> void bulkIndex(List<T> list, String indexName) {
        Bulk.Builder bulk = new Bulk.Builder();
        for (T o : list) {
            Index index = new Index.Builder(o).id(o.getPK()).index(indexName).type(o.getType()).build();
            bulk.addAction(index);
        }
        try {
            jestClient.execute(bulk.build());
        } catch (IOException e) {
            LOG.warn("bulkIndex again!! error={} index={}", e.getMessage(), indexName);
            sleep(100);
            bulkIndex(list, indexName);
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
            LOG.warn("insertOrUpdateDocumentById again!! error={} id={}", e.getMessage(), uniqueId);
            sleep(100);
            insertOrUpdateDocumentById(o, index, type, uniqueId);
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
            LOG.warn("deleteDocumentById again!! error={} id={}", e.getMessage(), id);
            sleep(100);
            deleteDocumentById(index, type, id);
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
            LOG.warn("getDocumentById again!! error={} id=");
            sleep(100);
            getDocumentById(object, index, id);
        }
        return o;
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            LOG.error("Thread sleep failure!! error={}", e.getMessage());
        }
    }

}
