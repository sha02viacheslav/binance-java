package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class MarginSummaryEntry {

    private String email;

    private BigDecimal totalAssetOfBtc;

    private BigDecimal totalLiabilityOfBtc;

    private BigDecimal totalNetAssetOfBtc;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getTotalAssetOfBtc() {
        return totalAssetOfBtc;
    }

    public void setTotalAssetOfBtc(BigDecimal totalAssetOfBtc) {
        this.totalAssetOfBtc = totalAssetOfBtc;
    }

    public BigDecimal getTotalLiabilityOfBtc() {
        return totalLiabilityOfBtc;
    }

    public void setTotalLiabilityOfBtc(BigDecimal totalLiabilityOfBtc) {
        this.totalLiabilityOfBtc = totalLiabilityOfBtc;
    }

    public BigDecimal getTotalNetAssetOfBtc() {
        return totalNetAssetOfBtc;
    }

    public void setTotalNetAssetOfBtc(BigDecimal totalNetAssetOfBtc) {
        this.totalNetAssetOfBtc = totalNetAssetOfBtc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("email", email)
                .append("totalAssetOfBtc", totalAssetOfBtc).append("totalLiabilityOfBtc", totalLiabilityOfBtc)
                .append("totalNetAssetOfBtc", totalNetAssetOfBtc).toString();
    }
}
