package com.blockchaindata.stockmarketmacdcalculate;

import com.blockchaindata.stockmarketcommon.domain.MacdData;
import com.blockchaindata.stockmarketcommon.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Andon
 * @date 2019/2/20
 */
@Component
public class Main implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    @Override
    public void run(ApplicationArguments args) {
        String format = TimeUtil.FORMAT.get().format(new Date());
        System.out.println(format);

        long timeId = TimeUtil.getTimeId(format);
        System.out.println(timeId);

        System.out.println(TimeUtil.FORMAT_HOUR.get().format(new Date()));
        System.out.println(TimeUtil.FORMAT_MIN.get().format(new Date()));

        MacdData macdData = new MacdData();
        macdData.setStockCode("000002.XSHE");
        macdData.setTimeType("5min");
        macdData.setTimeId(System.currentTimeMillis() / 1000);
        System.out.println(macdData.getId());
        LOG.info("Main run success!!");
    }
}
