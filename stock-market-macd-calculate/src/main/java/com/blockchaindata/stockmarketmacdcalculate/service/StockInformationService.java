package com.blockchaindata.stockmarketmacdcalculate.service;

import com.blockchaindata.stockmarketcommon.util.TimeUtil;
import com.blockchaindata.stockmarketmacdcalculate.dao.StockInformationRepository;
import com.blockchaindata.stockmarketmacdcalculate.domain.StockInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Andon
 * @date 2019/2/20
 */
@Service
@Transactional
public class StockInformationService {

    @Resource
    private StockInformationRepository stockInformationRepository;

    public List<StockInformation> findAllByEndDateAfterNow(){
        String endDate = TimeUtil.FORMAT.get().format(System.currentTimeMillis());
        return stockInformationRepository.findAllByEndDateAfter(endDate);
    }

    public List<String> findAllStockCodeNowAfterNow(){
        String endDate = TimeUtil.FORMAT.get().format(System.currentTimeMillis());
        return stockInformationRepository.findAllStockCodeAfter(endDate);
    }
}
