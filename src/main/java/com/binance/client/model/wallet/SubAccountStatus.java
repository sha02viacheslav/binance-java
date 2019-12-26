package com.binance.client.model.wallet;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SubAccountStatus {

    private String email;

    private String isSubUserEnabled;

    private Boolean isUserActive;

    private Integer insertTime;

    private String isMarginEnabled;

    private String isFutureEnabled;

    private Integer mobile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsSubUserEnabled() {
        return isSubUserEnabled;
    }

    public void setIsSubUserEnabled(String isSubUserEnabled) {
        this.isSubUserEnabled = isSubUserEnabled;
    }

    public Boolean getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(Boolean isUserActive) {
        this.isUserActive = isUserActive;
    }

    public Integer getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Integer insertTime) {
        this.insertTime = insertTime;
    }

    public String getIsMarginEnabled() {
        return isMarginEnabled;
    }

    public void setIsMarginEnabled(String isMarginEnabled) {
        this.isMarginEnabled = isMarginEnabled;
    }

    public String getIsFutureEnabled() {
        return isFutureEnabled;
    }

    public void setIsFutureEnabled(String isFutureEnabled) {
        this.isFutureEnabled = isFutureEnabled;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("email", email)
                .append("isSubUserEnabled", isSubUserEnabled).append("isUserActive", isUserActive)
                .append("insertTime", insertTime).append("isMarginEnabled", isMarginEnabled)
                .append("isFutureEnabled", isFutureEnabled).append("mobile", mobile).toString();
    }
}
