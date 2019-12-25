package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.math.BigDecimal;

public class Network {

    private String addressRegex;

    private String coin;

    private String depositDesc;

    private Boolean depositEnable;

    private Boolean isDefault;

    private String memoRegex;

    private String name;

    private String network;

    private Boolean resetAddressStatus;

    private String specialTips;

    private String withdrawDesc;

    private Boolean withdrawEnable;

    private BigDecimal withdrawFee;

    private BigDecimal withdrawIntegerMultiple;

    private BigDecimal withdrawMin;

    public String getAddressRegex() {
        return addressRegex;
    }

    public void setAddressRegex(String addressRegex) {
        this.addressRegex = addressRegex;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getDepositDesc() {
        return depositDesc;
    }

    public void setDepositDesc(String depositDesc) {
        this.depositDesc = depositDesc;
    }

    public Boolean getDepositEnable() {
        return depositEnable;
    }

    public void setDepositEnable(Boolean depositEnable) {
        this.depositEnable = depositEnable;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getMemoRegex() {
        return memoRegex;
    }

    public void setMemoRegex(String memoRegex) {
        this.memoRegex = memoRegex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Boolean getResetAddressStatus() {
        return resetAddressStatus;
    }

    public void setResetAddressStatus(Boolean resetAddressStatus) {
        this.resetAddressStatus = resetAddressStatus;
    }

    public String getSpecialTips() {
        return specialTips;
    }

    public void setSpecialTips(String specialTips) {
        this.specialTips = specialTips;
    }

    public String getWithdrawDesc() {
        return withdrawDesc;
    }

    public void setWithdrawDesc(String withdrawDesc) {
        this.withdrawDesc = withdrawDesc;
    }

    public Boolean getWithdrawEnable() {
        return withdrawEnable;
    }

    public void setWithdrawEnable(Boolean withdrawEnable) {
        this.withdrawEnable = withdrawEnable;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public BigDecimal getWithdrawIntegerMultiple() {
        return withdrawIntegerMultiple;
    }

    public void setWithdrawIntegerMultiple(BigDecimal withdrawIntegerMultiple) {
        this.withdrawIntegerMultiple = withdrawIntegerMultiple;
    }

    public BigDecimal getWithdrawMin() {
        return withdrawMin;
    }

    public void setWithdrawMin(BigDecimal withdrawMin) {
        this.withdrawMin = withdrawMin;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("addressRegex", addressRegex).append("coin", coin).append("depositDesc", depositDesc)
                .append("depositEnable", depositEnable).append("isDefault", isDefault).append("memoRegex", memoRegex)
                .append("name", name).append("network", network).append("resetAddressStatus", resetAddressStatus)
                .append("specialTips", specialTips).append("withdrawDesc", withdrawDesc)
                .append("withdrawEnable", withdrawEnable).append("withdrawFee", withdrawFee)
                .append("withdrawIntegerMultiple", withdrawIntegerMultiple).append("withdrawMin", withdrawMin)
                .toString();
    }
}
