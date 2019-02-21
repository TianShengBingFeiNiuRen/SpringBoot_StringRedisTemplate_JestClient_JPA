package com.blockchaindata.stockmarketmacdcalculate.service;

import com.blockchaindata.stockmarketcommon.domain.MacdData;
import com.blockchaindata.stockmarketcommon.domain.MacdExpectedPrice;
import com.blockchaindata.stockmarketcommon.domain.MacdSignal;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Andon
 * @date 2019/2/21
 */
@Service
public class EsService {

    private static final Logger LOG = LoggerFactory.getLogger(EsService.class);

    @Resource
    private JestClient jestClient;

    SearchResult jsonSearch(String json, String indexName, String typeName) {
        Search search = new Search.Builder(json).addIndex(indexName).addType(typeName).build();
        try {
            return jestClient.execute(search);
        } catch (Exception e) {
            LOG.warn("index:{}, type:{}, search again!! error = {}", indexName, typeName, e.getMessage());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e1) {
                LOG.error(e.getMessage(), e);
            }
            return jsonSearch(json, indexName, typeName);
        }
    }

    MacdData getMacdDataById(String index, String type, String id) {
        Get get = new Get.Builder(index, id).type(type).build();
        try {
            DocumentResult documentResult = jestClient.execute(get);
            return documentResult.getSourceAsObject(MacdData.class);
        } catch (IOException e) {
            LOG.warn("getMacdDataById again!! error={} id={}", e.getMessage(), id);
            return getMacdDataById(index, type, id);
        }
    }

    MacdData getMacdDataByIdAgain(String index, String type, String id) {
        Get get = new Get.Builder(index, id).type(type).build();
        try {
            DocumentResult documentResult = jestClient.execute(get);
            MacdData data = documentResult.getSourceAsObject(MacdData.class);
            if (!ObjectUtils.isEmpty(data)) {
                LOG.info("getMacdDataByIdAgain success!! id={}", id);
            }
            return data;
        } catch (IOException e) {
            LOG.error("getMacdDataById failure!! error={} id={}", e.getMessage(), id);
            return null;
        }
    }

    public void insertOrUpdateMacdData(MacdData macdData, String id, String index, String type) {
        Index.Builder builder = new Index.Builder(macdData).id(id).refresh(true);
        Index indexDoc = builder.index(index).type(type).build();
        try {
            jestClient.execute(indexDoc);
        } catch (IOException e) {
            LOG.warn("insertOrUpdateMacdData again!! error={} id={}", e.getMessage(), id);
            insertOrUpdateMacdData(macdData, id, index, type);
        }
    }

    void insertOrUpdateMacdSignal(MacdSignal macdSignal, String id, String index, String type) {
        Index.Builder builder = new Index.Builder(macdSignal).id(id).refresh(true);
        Index indexDoc = builder.index(index).type(type).build();
        try {
            jestClient.execute(indexDoc);
        } catch (IOException e) {
            LOG.warn("insertOrUpdateMacdSignal again!! error={} id={}", e.getMessage(), id);
            insertOrUpdateMacdSignal(macdSignal, id, index, type);
        }
    }

    void insertOrUpdateMacdExpectedPrice(MacdExpectedPrice macdExpectedPrice, String id, String index, String type) {
        Index.Builder builder = new Index.Builder(macdExpectedPrice).id(id).refresh(true);
        Index indexDoc = builder.index(index).type(type).build();
        try {
            jestClient.execute(indexDoc);
        } catch (IOException e) {
            LOG.warn("insertOrUpdateMacdPrice again!! error={} id={}", e.getMessage(), id);
            insertOrUpdateMacdExpectedPrice(macdExpectedPrice, id, index, type);
        }
    }

}
