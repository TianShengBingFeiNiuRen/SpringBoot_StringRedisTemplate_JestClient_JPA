package com.blockchaindata.stockmarketmacdcalculate.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Andon
 * @date 2019/2/20
 */
@Entity
@Table(name = "stock_security")
public class StockInformation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id; //id

    @Column(name = "stock_code")
    private String stockCode; //股票代码

    @Column(name = "stock_display_name")
    private String stockDisplayName; //股票名称

    @Column(name = "stock_name")
    private String stockName; //股票拼音缩写（大写）

    @Column(name = "start_date")
    private String startDate; //上市时间:2019-02-18 14:57:00

    @Column(name = "end_date")
    private String endDate; //退市时间:2200-01-01 0:00:00

    @Column(name = "type")
    private String type; //标的类型

    @Column(name = "pe_ratio")
    private String peRatio; //市盈率

    @Column(name = "turnover_ratio")
    private String turnoverRatio; //换手率

    @Column(name = "pb_ratio")
    private String pbRatio; //市净率

    @Column(name = "ps_ratio")
    private String psRatio; //市销率

    @Column(name = "pcf_ratio")
    private String pcfRatio; //市现率

    @Column(name = "capitalization")
    private String capitalization; //总股本

    @Column(name = "market_cap")
    private String marketCap; //总市值

    @Column(name = "circulating_cap")
    private String circulatingCap; //流通股本

    @Column(name = "circulating_market_cap")
    private String circulatingMarketCap; //流通市值

    @Column(name = "day")
    private String day; //日期

    @Column(name = "pe_ratio_lyr")
    private String peRatioLyr; //市盈率

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockDisplayName() {
        return stockDisplayName;
    }

    public void setStockDisplayName(String stockDisplayName) {
        this.stockDisplayName = stockDisplayName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(String peRatio) {
        this.peRatio = peRatio;
    }

    public String getTurnoverRatio() {
        return turnoverRatio;
    }

    public void setTurnoverRatio(String turnoverRatio) {
        this.turnoverRatio = turnoverRatio;
    }

    public String getPbRatio() {
        return pbRatio;
    }

    public void setPbRatio(String pbRatio) {
        this.pbRatio = pbRatio;
    }

    public String getPsRatio() {
        return psRatio;
    }

    public void setPsRatio(String psRatio) {
        this.psRatio = psRatio;
    }

    public String getPcfRatio() {
        return pcfRatio;
    }

    public void setPcfRatio(String pcfRatio) {
        this.pcfRatio = pcfRatio;
    }

    public String getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(String capitalization) {
        this.capitalization = capitalization;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getCirculatingCap() {
        return circulatingCap;
    }

    public void setCirculatingCap(String circulatingCap) {
        this.circulatingCap = circulatingCap;
    }

    public String getCirculatingMarketCap() {
        return circulatingMarketCap;
    }

    public void setCirculatingMarketCap(String circulatingMarketCap) {
        this.circulatingMarketCap = circulatingMarketCap;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPeRatioLyr() {
        return peRatioLyr;
    }

    public void setPeRatioLyr(String peRatioLyr) {
        this.peRatioLyr = peRatioLyr;
    }

    @Override
    public String toString() {
        return "StockInformation{" +
                "id=" + id +
                ", stockCode='" + stockCode + '\'' +
                ", stockDisplayName='" + stockDisplayName + '\'' +
                ", stockName='" + stockName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", type='" + type + '\'' +
                ", peRatio='" + peRatio + '\'' +
                ", turnoverRatio='" + turnoverRatio + '\'' +
                ", pbRatio='" + pbRatio + '\'' +
                ", psRatio='" + psRatio + '\'' +
                ", pcfRatio='" + pcfRatio + '\'' +
                ", capitalization='" + capitalization + '\'' +
                ", marketCap='" + marketCap + '\'' +
                ", circulatingCap='" + circulatingCap + '\'' +
                ", circulatingMarketCap='" + circulatingMarketCap + '\'' +
                ", day='" + day + '\'' +
                ", peRatioLyr='" + peRatioLyr + '\'' +
                '}';
    }
}
