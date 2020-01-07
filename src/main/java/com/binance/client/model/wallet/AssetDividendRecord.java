package com.binance.client.model.wallet;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class AssetDividendRecord {

    private BigDecimal amount;

    private String asset;

    private Long divTime;

    private String enInfo;

    private Long tranId;

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

    public Long getDivTime() {
        return divTime;
    }

    public void setDivTime(Long divTime) {
        this.divTime = divTime;
    }

    public String getEnInfo() {
        return enInfo;
    }

    public void setEnInfo(String enInfo) {
        this.enInfo = enInfo;
    }

    public Long getTranId() {
        return tranId;
    }

    public void setTranId(Long tranId) {
        this.tranId = tranId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("amount", amount)
                .append("asset", asset).append("divTime", divTime).append("enInfo", enInfo).append("tranId", tranId)
                .toString();
    }
}
