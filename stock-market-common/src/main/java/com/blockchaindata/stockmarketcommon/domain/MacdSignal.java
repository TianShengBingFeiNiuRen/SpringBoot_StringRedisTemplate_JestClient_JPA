package com.blockchaindata.stockmarketcommon.domain;

import java.io.*;

/**
 * @author Andon
 * @date 2019/2/19
 */
public class MacdSignal implements Serializable {

    private String stockCode; //股票代码:000001.XSHE
    private String timeType; //时间粒度
    private String macdType; //macd信号类型
    private String time; //时间:2019-02-18 14:57:00
    private long timeId; //时间戳
    private double difLast; //上根k线dif
    private double dea1Last; //上根k线dea
    private double bar1Last; //上根k线柱线
    private double closePriceLast; //上根k线收盘价
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

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getMacdType() {
        return macdType;
    }

    public void setMacdType(String macdType) {
        this.macdType = macdType;
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

    public double getDifLast() {
        return difLast;
    }

    public void setDifLast(double difLast) {
        this.difLast = difLast;
    }

    public double getDea1Last() {
        return dea1Last;
    }

    public void setDea1Last(double dea1Last) {
        this.dea1Last = dea1Last;
    }

    public double getBar1Last() {
        return bar1Last;
    }

    public void setBar1Last(double bar1Last) {
        this.bar1Last = bar1Last;
    }

    public double getClosePriceLast() {
        return closePriceLast;
    }

    public void setClosePriceLast(double closePriceLast) {
        this.closePriceLast = closePriceLast;
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

    public String getId() {
        return stockCode + "_" + timeType + "_" + timeId + "_" + macdType;
    }

    @Override
    public String toString() {
        return "MacdSignal{" +
                "stockCode='" + stockCode + '\'' +
                ", timeType='" + timeType + '\'' +
                ", macdType='" + macdType + '\'' +
                ", time='" + time + '\'' +
                ", timeId=" + timeId +
                ", difLast=" + difLast +
                ", dea1Last=" + dea1Last +
                ", bar1Last=" + bar1Last +
                ", closePriceLast=" + closePriceLast +
                ", dif=" + dif +
                ", dea=" + dea +
                ", bar=" + bar +
                ", closePrice=" + closePrice +
                ", remark='" + remark + '\'' +
                ", id='" + getId() + '\'' +
                '}';
    }

    public MacdSignal myClone() {
        MacdSignal macdSignal = null;
        try {
            // 写入字节流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            macdSignal = (MacdSignal) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return macdSignal;
    }
}
