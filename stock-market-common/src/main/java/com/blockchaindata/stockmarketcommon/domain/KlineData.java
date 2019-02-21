package com.blockchaindata.stockmarketcommon.domain;

import java.io.Serializable;

/**
 * @author Andon
 * @date 2019/2/19
 */
public class KlineData implements Serializable {

    private String code; //股票代码:000001.XSHE
    private String date; //时间:2019-02-18 14:57:00
    private double open; //开盘价
    private double close; //收盘价
    private double high; //最高价
    private double low; //最低价
    private double volume; //成交量
    private double money; //成交额

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "KlineData{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                ", money=" + money +
                '}';
    }
}
