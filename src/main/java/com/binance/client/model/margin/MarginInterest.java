package com.binance.client.model.margin;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class MarginInterest {

    private String asset;

    private BigDecimal interest;

    private Long interestAccuredTime;

    private BigDecimal interestRate;

    private BigDecimal principal;

    private String type;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Long getInterestAccuredTime() {
        return interestAccuredTime;
    }

    public void setInterestAccuredTime(Long interestAccuredTime) {
        this.interestAccuredTime = interestAccuredTime;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("asset", asset)
                .append("interest", interest).append("interestAccuredTime", interestAccuredTime)
                .append("interestRate", interestRate).append("principal", principal).append("type", type).toString();
    }
}
