package com.blockchaindata.stockmarketmacdcalculate;

import com.blockchaindata.stockmarketcommon.domain.KlineData;
import com.blockchaindata.stockmarketcommon.domain.MacdData;
import com.blockchaindata.stockmarketcommon.domain.MacdSignalForRedis;
import com.blockchaindata.stockmarketcommon.util.GsonUtil;
import com.blockchaindata.stockmarketmacdcalculate.service.EsService;
import com.blockchaindata.stockmarketmacdcalculate.service.RedisService;
import com.blockchaindata.stockmarketmacdcalculate.service.StockInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Andon
 * @date 2019/2/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Value("${redis.key.macdSignal}")
    private String macdSignalForRedisKey;
    @Value("${es.index.macdData}")
    private String macdDataIndex;
    @Value("${kLineDifference_4}")
    private long kLineDifference4;
    @Value("${kLineDifference_7}")
    private long kLineDifference7;
    @Resource
    private StockInformationService stockInformationService;
    @Resource
    private RedisService redisService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private EsService esService;

    @Test
    public void StockInformationServiceTest() {

        List<String> allStockCodeNowAfterNow = stockInformationService.findAllStockCodeNowAfterNow();
        allStockCodeNowAfterNow.forEach(System.out::println);
        System.out.println("allByEndDateAfterNow.size()=" + allStockCodeNowAfterNow.size());
    }

    @Test
    public void stringRedisTemplateTest() {
        HashOperations<String, String, String> stringStringStringHashOperations = stringRedisTemplate.opsForHash();
        // k线数据
        KlineData klineData = new KlineData();
        klineData.setCode("000001.XSHE");
        klineData.setDate("2019-02-19 13:05:00");
        klineData.setOpen(11.37);
        klineData.setClose(11.38);
        klineData.setHigh(11.38);
        klineData.setLow(11.37);
        klineData.setVolume(167200);
        klineData.setMoney(1902149);
        // 信号数据
        MacdSignalForRedis macdSignalForRedis = new MacdSignalForRedis();
        macdSignalForRedis.setBottomLowestPrice1(3873.23);
        macdSignalForRedis.setBottomCha1(39.27118701414475);
        macdSignalForRedis.setBottomLowestPrice2(3886.71);
        macdSignalForRedis.setBottomCha2(999);
        macdSignalForRedis.setBottomStage("2");
        macdSignalForRedis.setTopHighestPrice1(3983.35);
        macdSignalForRedis.setTopCha1(39.592351472096425);
        macdSignalForRedis.setTopHighestPrice2(0);
        macdSignalForRedis.setTopCha2(-999);
        macdSignalForRedis.setTopStage("2");
        macdSignalForRedis.setTimeIdJinCha1(1550591940000L);
        macdSignalForRedis.setTimeIdSiCha1(1550599140000L);
        String key = klineData.getCode().split("\\.")[0];
        System.out.println("key=" + key);

        stringStringStringHashOperations.put(macdSignalForRedisKey, key, GsonUtil.GSON.toJson(macdSignalForRedis));
        String str = stringStringStringHashOperations.get(macdSignalForRedisKey, key);
        System.out.println(str);
    }

    @Test
    public void esServiceTest() {
        MacdData macdData = new MacdData();
        macdData.setStockCode("000001.XSHE");
        macdData.setTime("2019-02-18 14:57:00");
        macdData.setTimeId(1550473020);
        macdData.setTimeType("5min");
        macdData.setEma12(12);
        macdData.setEma26(26);
        macdData.setDif(2);
        macdData.setDea(1);
        macdData.setBar(2);
        macdData.setClosePrice(22);
        String id = macdData.getId();
        String index = macdDataIndex;
        String type = index + "_" + macdData.getTime().split(" ")[0].replace("-", "");
        System.out.println("id=" + id);
        System.out.println("index=" + index);
        System.out.println("type=" + type);
        esService.insertOrUpdateMacdData(macdData, id, macdDataIndex, type);
    }
}
