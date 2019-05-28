package com.blockchaindata.stockmarketmacdcalculate;

import com.blockchaindata.stockmarketcommon.domain.EsTestModel;
import com.blockchaindata.stockmarketcommon.util.TimeUtil;
import com.blockchaindata.stockmarketmacdcalculate.service.EsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author Andon
 * @date 2019/5/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsServiceTest {

    private String indexName;

    @Resource
    private EsService esService;

    @PostConstruct
    public void init() {
        indexName = "es_test_index";
    }

    @Test
    public void insertOrUpdateDocumentByIdTest() {
        long l = System.currentTimeMillis();
        EsTestModel esTestModel = new EsTestModel();
        esTestModel.setStr("I love java !!");
        esTestModel.setTimestamp(l / 1000);
        esTestModel.setTime(TimeUtil.FORMAT.get().format(l));

        System.out.println(esTestModel);
        esService.insertOrUpdateDocumentById(esTestModel, indexName, esTestModel.getType(), esTestModel.getPK());
        System.out.println("insertOrUpdateDocumentById success!!");
    }

    @Test
    public void deleteDocumentByIdTest() {
        String id = "Hello_1558952750";
        String type = "2019-05-27";
        esService.deleteDocumentById(indexName, type, id);
        System.out.println("deleteDocumentById success!!");
    }

    @Test
    public void getDocumentByIdTest() {
        String id = "World_1559008005";
        EsTestModel esTestModel = new EsTestModel();
        esTestModel = esService.getDocumentById(esTestModel, indexName, id);
        System.out.println("getDocumentById success!!");
        System.out.println(esTestModel);
    }

    @Test
    public void bulkIndexTest() {
        ArrayList<EsTestModel> esTestModels = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            long l = System.currentTimeMillis();
            EsTestModel esTestModel = new EsTestModel();
            esTestModel.setStr(String.valueOf(i));
            esTestModel.setTimestamp(l / 1000);
            esTestModel.setTime(TimeUtil.FORMAT.get().format(l));
            esTestModels.add(esTestModel);
        }
        long l = System.currentTimeMillis();
        esService.bulkIndex(esTestModels, indexName);
        System.out.println("bulkIndexTestSpendTime: " + (System.currentTimeMillis() - l) + "ms");
    }

    @Test
    public void bulkTest() {
        ArrayList<EsTestModel> esTestModels = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            long l = System.currentTimeMillis();
            EsTestModel esTestModel = new EsTestModel();
            esTestModel.setStr(String.valueOf(i));
            esTestModel.setTimestamp(l / 1000);
            esTestModel.setTime(TimeUtil.FORMAT.get().format(l));
            esTestModels.add(esTestModel);
        }

        long l = System.currentTimeMillis();
        for (EsTestModel esTestModel : esTestModels) {
            esService.insertOrUpdateDocumentById(esTestModel, indexName, esTestModel.getType(), esTestModel.getPK());
        }
        System.out.println("bulkTestSpendTime: " + (System.currentTimeMillis() - l) + "ms");
    }
}
