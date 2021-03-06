package com.blockchaindata.stockmarketcommon.domain;

import java.io.Serializable;

/**
 * @author Andon
 * 2019/5/27
 */
public abstract class BaseModel implements Serializable {

    public abstract String getPK();

    public abstract String getType();
}
