package com.binance.client.model.margin;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

public class MarginAccount {

    private Boolean borrowEnabled;

    private BigDecimal marginLevel;

    private BigDecimal totalAssetOfBtc;

    private BigDecimal totalLiabilityOfBtc;

    private BigDecimal totalNetAssetOfBtc;

    private Boolean tradeEnabled;

    private Boolean transferEnabled;

    private List<MarginAccountAsset> userAssets;

    public Boolean getBorrowEnabled() {
        return borrowEnabled;
    }

    public void setBorrowEnabled(Boolean borrowEnabled) {
        this.borrowEnabled = borrowEnabled;
    }

    public BigDecimal getMarginLevel() {
        return marginLevel;
    }

    public void setMarginLevel(BigDecimal marginLevel) {
        this.marginLevel = marginLevel;
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

    public Boolean getTradeEnabled() {
        return tradeEnabled;
    }

    public void setTradeEnabled(Boolean tradeEnabled) {
        this.tradeEnabled = tradeEnabled;
    }

    public Boolean getTransferEnabled() {
        return transferEnabled;
    }

    public void setTransferEnabled(Boolean transferEnabled) {
        this.transferEnabled = transferEnabled;
    }

    public List<MarginAccountAsset> getUserAssets() {
        return userAssets;
    }

    public void setUserAssets(List<MarginAccountAsset> userAssets) {
        this.userAssets = userAssets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("borrowEnabled", borrowEnabled).append("marginLevel", marginLevel)
                .append("totalAssetOfBtc", totalAssetOfBtc).append("totalLiabilityOfBtc", totalLiabilityOfBtc)
                .append("totalNetAssetOfBtc", totalNetAssetOfBtc).append("tradeEnabled", tradeEnabled)
                .append("transferEnabled", transferEnabled).append("userAssets", userAssets).toString();
    }
}
