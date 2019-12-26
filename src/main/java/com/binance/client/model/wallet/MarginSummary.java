package com.binance.client.model.wallet;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

public class MarginSummary {

    private BigDecimal totalAssetOfBtc;

    private BigDecimal totalLiabilityOfBtc;

    private BigDecimal totalNetAssetOfBtc;

    private List<MarginSummaryEntry> subAccountList;

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

    public List<MarginSummaryEntry> getSubAccountList() {
        return subAccountList;
    }

    public void setSubAccountList(List<MarginSummaryEntry> subAccountList) {
        this.subAccountList = subAccountList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("totalAssetOfBtc", totalAssetOfBtc).append("totalLiabilityOfBtc", totalLiabilityOfBtc)
                .append("totalNetAssetOfBtc", totalNetAssetOfBtc).append("subAccountList", subAccountList).toString();
    }
}
