package com.blockchaindata.stockmarketcommon.domain;

import java.io.Serializable;

/**
 * @author Andon
 * @date 2019/2/19
 */
public class MacdExpectedPrice implements Serializable {

    private String stockCode; //股票代码:000001.XSHE
    private String timeType; //时间粒度
    private String expectedMacdType; //期望类型
    private String time; //时间:2019-02-18 14:57:00
    private long timeId; //时间戳
    private double closePrice; //现收盘价
    private double expectedPrice; //期望价格
    private double differenceRatio; //差值比例
    private String remark; //备注

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getExpectedMacdType() {
        return expectedMacdType;
    }

    public void setExpectedMacdType(String expectedMacdType) {
        this.expectedMacdType = expectedMacdType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getTimeId() {
        return timeId;
    }

    public void setTimeId(long timeId) {
        this.timeId = timeId;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public double getDifferenceRatio() {
        return differenceRatio;
    }

    public void setDifferenceRatio(double differenceRatio) {
        this.differenceRatio = differenceRatio;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return stockCode + "_" + timeType + "_" + timeId + "_" + expectedMacdType;
    }

    @Override
    public String toString() {
        return "MacdExpectedPrice{" +
                "stockCode='" + stockCode + '\'' +
                ", timeType='" + timeType + '\'' +
                ", expectedMacdType='" + expectedMacdType + '\'' +
                ", time='" + time + '\'' +
                ", timeId=" + timeId +
                ", closePrice=" + closePrice +
                ", expectedPrice=" + expectedPrice +
                ", differenceRatio=" + differenceRatio +
                ", remark='" + remark + '\'' +
                ", id='" + getId() + '\'' +
                '}';
    }
}
