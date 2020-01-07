package com.binance.client.model.user;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class BalanceUpdate {

    private String asset;

    private BigDecimal delta;

    private Long clearTime;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getDelta() {
        return delta;
    }

    public void setDelta(BigDecimal delta) {
        this.delta = delta;
    }

    public Long getClearTime() {
        return clearTime;
    }

    public void setClearTime(Long clearTime) {
        this.clearTime = clearTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("asset", asset)
                .append("delta", delta).append("clearTime", clearTime).toString();
    }
}
