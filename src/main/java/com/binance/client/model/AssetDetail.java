package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class AssetDetail {

    private String asset;

    private BigDecimal minWithdrawAmount;

    private Boolean depositStatus;

    private BigDecimal withdrawFee;

    private Boolean withdrawStatus;

    private String depositTip;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getMinWithdrawAmount() {
        return minWithdrawAmount;
    }

    public void setMinWithdrawAmount(BigDecimal minWithdrawAmount) {
        this.minWithdrawAmount = minWithdrawAmount;
    }

    public Boolean getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(Boolean depositStatus) {
        this.depositStatus = depositStatus;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public Boolean getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(Boolean withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public String getDepositTip() {
        return depositTip;
    }

    public void setDepositTip(String depositTip) {
        this.depositTip = depositTip;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("asset", asset)
                .append("minWithdrawAmount", minWithdrawAmount).append("depositStatus", depositStatus)
                .append("withdrawFee", withdrawFee).append("withdrawStatus", withdrawStatus)
                .append("depositTip", depositTip).toString();
    }
}
