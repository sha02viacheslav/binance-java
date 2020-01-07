package com.binance.client.model.wallet;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.math.BigDecimal;

public class SubAccountFuturesDetail {

    private String email;

    private Boolean canDeposit;

    private Boolean canTrade;

    private Boolean canWithdraw;

    private BigDecimal feeTier;

    private BigDecimal maxWithdrawAmount;

    private BigDecimal totalInitialMargin;

    private BigDecimal totalMaintMargin;

    private BigDecimal totalMarginBalance;

    private BigDecimal totalOpenOrderInitialMargin;

    private BigDecimal totalPositionInitialMargin;

    private BigDecimal totalUnrealizedProfit;

    private BigDecimal totalWalletBalance;

    private String asset;

    private Long updateTime;

    private List<SubAccountFuturesDetailAsset> assets;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(Boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public Boolean getCanTrade() {
        return canTrade;
    }

    public void setCanTrade(Boolean canTrade) {
        this.canTrade = canTrade;
    }

    public Boolean getCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(Boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public BigDecimal getFeeTier() {
        return feeTier;
    }

    public void setFeeTier(BigDecimal feeTier) {
        this.feeTier = feeTier;
    }

    public BigDecimal getMaxWithdrawAmount() {
        return maxWithdrawAmount;
    }

    public void setMaxWithdrawAmount(BigDecimal maxWithdrawAmount) {
        this.maxWithdrawAmount = maxWithdrawAmount;
    }

    public BigDecimal getTotalInitialMargin() {
        return totalInitialMargin;
    }

    public void setTotalInitialMargin(BigDecimal totalInitialMargin) {
        this.totalInitialMargin = totalInitialMargin;
    }

    public BigDecimal getTotalMaintMargin() {
        return totalMaintMargin;
    }

    public void setTotalMaintMargin(BigDecimal totalMaintMargin) {
        this.totalMaintMargin = totalMaintMargin;
    }

    public BigDecimal getTotalMarginBalance() {
        return totalMarginBalance;
    }

    public void setTotalMarginBalance(BigDecimal totalMarginBalance) {
        this.totalMarginBalance = totalMarginBalance;
    }

    public BigDecimal getTotalOpenOrderInitialMargin() {
        return totalOpenOrderInitialMargin;
    }

    public void setTotalOpenOrderInitialMargin(BigDecimal totalOpenOrderInitialMargin) {
        this.totalOpenOrderInitialMargin = totalOpenOrderInitialMargin;
    }

    public BigDecimal getTotalPositionInitialMargin() {
        return totalPositionInitialMargin;
    }

    public void setTotalPositionInitialMargin(BigDecimal totalPositionInitialMargin) {
        this.totalPositionInitialMargin = totalPositionInitialMargin;
    }

    public BigDecimal getTotalUnrealizedProfit() {
        return totalUnrealizedProfit;
    }

    public void setTotalUnrealizedProfit(BigDecimal totalUnrealizedProfit) {
        this.totalUnrealizedProfit = totalUnrealizedProfit;
    }

    public BigDecimal getTotalWalletBalance() {
        return totalWalletBalance;
    }

    public void setTotalWalletBalance(BigDecimal totalWalletBalance) {
        this.totalWalletBalance = totalWalletBalance;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public List<SubAccountFuturesDetailAsset> getAssets() {
        return assets;
    }

    public void setAssets(List<SubAccountFuturesDetailAsset> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("email", email)
                .append("canDeposit", canDeposit).append("canTrade", canTrade).append("canWithdraw", canWithdraw)
                .append("feeTier", feeTier).append("maxWithdrawAmount", maxWithdrawAmount)
                .append("totalInitialMargin", totalInitialMargin).append("totalMaintMargin", totalMaintMargin)
                .append("totalMarginBalance", totalMarginBalance)
                .append("totalOpenOrderInitialMargin", totalOpenOrderInitialMargin)
                .append("totalPositionInitialMargin", totalPositionInitialMargin)
                .append("totalUnrealizedProfit", totalUnrealizedProfit).append("totalWalletBalance", totalWalletBalance)
                .append("asset", asset).append("updateTime", updateTime).append("assets", assets).toString();
    }
}
