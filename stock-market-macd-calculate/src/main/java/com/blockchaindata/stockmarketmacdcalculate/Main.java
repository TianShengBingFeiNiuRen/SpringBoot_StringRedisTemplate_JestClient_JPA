package com.blockchaindata.stockmarketmacdcalculate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Andon
 * @date 2019/2/20
 */
@Component
public class Main implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    @Override
    public void run(ApplicationArguments args) {
        LOG.info("Main run!!");
    }
}
