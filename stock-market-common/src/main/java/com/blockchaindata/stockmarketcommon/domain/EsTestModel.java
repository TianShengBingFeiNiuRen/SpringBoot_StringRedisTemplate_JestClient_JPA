package com.blockchaindata.stockmarketcommon.domain;

import com.blockchaindata.stockmarketcommon.util.TimeUtil;

import java.io.Serializable;

/**
 * @author Andon
 * @date 2019/5/27
 */
public class EsTestModel extends BaseModel implements Serializable {

    private String str;

    private long timestamp;

    private String time;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "EsTestModel{" +
                "str='" + str + '\'' +
                ", timestamp=" + timestamp +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public String getPK() {
        return str.replaceAll(" ", "") + "_" + timestamp;
    }

    @Override
    public String getType() {
        return TimeUtil.FORMAT_DAY.get().format(timestamp * 1000);
    }
}
