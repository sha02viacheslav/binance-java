package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class AssetDividendRecord {

    private BigDecimal amount;

    private String asset;

    private Integer divTime;

    private String enInfo;

    private Integer tranId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Integer getDivTime() {
        return divTime;
    }

    public void setDivTime(Integer divTime) {
        this.divTime = divTime;
    }

    public String getEnInfo() {
        return enInfo;
    }

    public void setEnInfo(String enInfo) {
        this.enInfo = enInfo;
    }

    public Integer getTranId() {
        return tranId;
    }

    public void setTranId(Integer tranId) {
        this.tranId = tranId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("amount", amount)
                .append("asset", asset).append("divTime", divTime).append("enInfo", enInfo).append("tranId", tranId)
                .toString();
    }
}
