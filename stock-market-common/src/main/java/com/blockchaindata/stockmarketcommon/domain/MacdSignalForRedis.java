package com.blockchaindata.stockmarketcommon.domain;

import java.io.Serializable;

/**
 * @author Andon
 * @date 2019/2/19
 */
public class MacdSignalForRedis implements Serializable {

    private double bottomLowestPrice1;
    private double bottomCha1;
    private double bottomLowestPrice2;
    private double bottomCha2;
    private String bottomStage;
    private double topHighestPrice1;
    private double topCha1;
    private double topHighestPrice2;
    private double topCha2;
    private String topStage;
    private long timeIdJinCha1;
    private long timeIdSiCha1;

    public double getBottomLowestPrice1() {
        return bottomLowestPrice1;
    }

    public void setBottomLowestPrice1(double bottomLowestPrice1) {
        this.bottomLowestPrice1 = bottomLowestPrice1;
    }

    public double getBottomCha1() {
        return bottomCha1;
    }

    public void setBottomCha1(double bottomCha1) {
        this.bottomCha1 = bottomCha1;
    }

    public double getBottomLowestPrice2() {
        return bottomLowestPrice2;
    }

    public void setBottomLowestPrice2(double bottomLowestPrice2) {
        this.bottomLowestPrice2 = bottomLowestPrice2;
    }

    public double getBottomCha2() {
        return bottomCha2;
    }

    public void setBottomCha2(double bottomCha2) {
        this.bottomCha2 = bottomCha2;
    }

    public String getBottomStage() {
        return bottomStage;
    }

    public void setBottomStage(String bottomStage) {
        this.bottomStage = bottomStage;
    }

    public double getTopHighestPrice1() {
        return topHighestPrice1;
    }

    public void setTopHighestPrice1(double topHighestPrice1) {
        this.topHighestPrice1 = topHighestPrice1;
    }

    public double getTopCha1() {
        return topCha1;
    }

    public void setTopCha1(double topCha1) {
        this.topCha1 = topCha1;
    }

    public double getTopHighestPrice2() {
        return topHighestPrice2;
    }

    public void setTopHighestPrice2(double topHighestPrice2) {
        this.topHighestPrice2 = topHighestPrice2;
    }

    public double getTopCha2() {
        return topCha2;
    }

    public void setTopCha2(double topCha2) {
        this.topCha2 = topCha2;
    }

    public String getTopStage() {
        return topStage;
    }

    public void setTopStage(String topStage) {
        this.topStage = topStage;
    }

    public long getTimeIdJinCha1() {
        return timeIdJinCha1;
    }

    public void setTimeIdJinCha1(long timeIdJinCha1) {
        this.timeIdJinCha1 = timeIdJinCha1;
    }

    public long getTimeIdSiCha1() {
        return timeIdSiCha1;
    }

    public void setTimeIdSiCha1(long timeIdSiCha1) {
        this.timeIdSiCha1 = timeIdSiCha1;
    }

    @Override
    public String toString() {
        return "MacdSignalForRedis{" +
                "bottomLowestPrice1=" + bottomLowestPrice1 +
                ", bottomCha1=" + bottomCha1 +
                ", bottomLowestPrice2=" + bottomLowestPrice2 +
                ", bottomCha2=" + bottomCha2 +
                ", bottomStage='" + bottomStage + '\'' +
                ", topHighestPrice1=" + topHighestPrice1 +
                ", topCha1=" + topCha1 +
                ", topHighestPrice2=" + topHighestPrice2 +
                ", topCha2=" + topCha2 +
                ", topStage='" + topStage + '\'' +
                ", timeIdJinCha1=" + timeIdJinCha1 +
                ", timeIdSiCha1=" + timeIdSiCha1 +
                '}';
    }
}
