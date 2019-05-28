package com.blockchaindata.stockmarketcommon.domain;

import java.io.Serializable;

/**
 * @author Andon
 * @date 2019/2/19
 */
public class MacdData extends BaseModel implements Serializable {

    private String stockCode; //股票代码:000001.XSHE
    private String time; //时间:2019-02-18 14:57:00
    private long timeId; //时间戳
    private String timeType; //时间粒度
    private double ema12; //ema12
    private double ema26; //ema26
    private double dif; //dif
    private double dea; //dea
    private double bar; //柱线
    private double closePrice; //收盘价
    private String remark; //备注

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
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

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public double getEma12() {
        return ema12;
    }

    public void setEma12(double ema12) {
        this.ema12 = ema12;
    }

    public double getEma26() {
        return ema26;
    }

    public void setEma26(double ema26) {
        this.ema26 = ema26;
    }

    public double getDif() {
        return dif;
    }

    public void setDif(double dif) {
        this.dif = dif;
    }

    public double getDea() {
        return dea;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }

    public double getBar() {
        return bar;
    }

    public void setBar(double bar) {
        this.bar = bar;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MacdData{" +
                "stockCode='" + stockCode + '\'' +
                ", time='" + time + '\'' +
                ", timeId=" + timeId +
                ", timeType='" + timeType + '\'' +
                ", ema12=" + ema12 +
                ", ema26=" + ema26 +
                ", dif=" + dif +
                ", dea=" + dea +
                ", bar=" + bar +
                ", closePrice=" + closePrice +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public String getPK() {
        return stockCode + "_" + timeType + "_" + timeId;
    }

    @Override
    public String getType() {
        return time.split(" ")[0];
    }
}
