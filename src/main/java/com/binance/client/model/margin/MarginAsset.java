package com.binance.client.model.margin;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class MarginAsset {

    private String assetFullName;

    private String assetName;

    private Boolean isBorrowable;

    private Boolean isMortgageable;

    private BigDecimal userMinBorrow;

    private BigDecimal userMinRepay;

    public String getAssetFullName() {
        return assetFullName;
    }

    public void setAssetFullName(String assetFullName) {
        this.assetFullName = assetFullName;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Boolean getIsBorrowable() {
        return isBorrowable;
    }

    public void setIsBorrowable(Boolean isBorrowable) {
        this.isBorrowable = isBorrowable;
    }

    public Boolean getIsMortgageable() {
        return isMortgageable;
    }

    public void setIsMortgageable(Boolean isMortgageable) {
        this.isMortgageable = isMortgageable;
    }

    public BigDecimal getUserMinBorrow() {
        return userMinBorrow;
    }

    public void setUserMinBorrow(BigDecimal userMinBorrow) {
        this.userMinBorrow = userMinBorrow;
    }

    public BigDecimal getUserMinRepay() {
        return userMinRepay;
    }

    public void setUserMinRepay(BigDecimal userMinRepay) {
        this.userMinRepay = userMinRepay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("assetFullName", assetFullName).append("assetName", assetName)
                .append("isBorrowable", isBorrowable).append("isMortgageable", isMortgageable)
                .append("userMinBorrow", userMinBorrow).append("userMinRepay", userMinRepay).toString();
    }
}
