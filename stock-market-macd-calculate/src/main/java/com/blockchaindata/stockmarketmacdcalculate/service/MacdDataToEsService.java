package com.blockchaindata.stockmarketmacdcalculate.service;

import com.blockchaindata.stockmarketcommon.domain.MacdData;
import com.blockchaindata.stockmarketcommon.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Andon
 * @date 2019/2/26
 */
@SuppressWarnings("Duplicates")
@Service
public class MacdDataToEsService implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(MacdDataToEsService.class);
    private CopyOnWriteArrayList<MacdData> macdDataCopyOnWriteArrayList;
    private AtomicLong timestamp;

    @Value("${es.index.macdData}")
    private String macdDataIndex;
    @Resource
    private EsService esService;

    @PostConstruct
    public void init() {
        macdDataCopyOnWriteArrayList = new CopyOnWriteArrayList<>();
        timestamp = new AtomicLong(0L);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            long l = System.currentTimeMillis();
            long timestampL = timestamp.get();
            if ((macdDataCopyOnWriteArrayList.size() > 3000 || (l - timestampL) > 3000) && !ObjectUtils.isEmpty(macdDataCopyOnWriteArrayList)) {
                esService.bulkIndex(macdDataIndex, macdDataCopyOnWriteArrayList);
                macdDataCopyOnWriteArrayList.clear();
                timestamp.set(l);
            }
            if (timestampL == 0) {
                timestamp.set(l);
            }
            TimeUtil.sleep(10);
        }
    }

    void putMacdDataToBulkList(MacdData macdData) {
        macdDataCopyOnWriteArrayList.add(macdData);
    }
}
