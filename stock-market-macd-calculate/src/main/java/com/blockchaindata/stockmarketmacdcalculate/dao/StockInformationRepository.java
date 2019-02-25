package com.blockchaindata.stockmarketmacdcalculate.dao;

import com.blockchaindata.stockmarketmacdcalculate.domain.StockInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Andon
 * @date 2019/2/20
 */
@Repository
public interface StockInformationRepository extends JpaRepository<StockInformation, Integer> {

    List<StockInformation> findAllByEndDateAfter(String endTime);

    @Query(value = "SELECT stockCode FROM StockInformation WHERE endDate > ?1")
    List<String> findAllStockCodeAfter(String endTime);
    
    @Query(nativeQuery = true, value = "SELECT stock_code FROM stock_finance")
    List<String> findAllStockCodeOfDividend();

    @Query(nativeQuery = true, value = "SELECT COUNT(id) FROM stock_trade_day WHERE trade_day = ?1")
    int nowIsItCurrentlyTradingDay(String nowTime);
}
