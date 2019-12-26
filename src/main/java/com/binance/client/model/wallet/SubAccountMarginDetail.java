package com.binance.client.model.wallet;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.math.BigDecimal;

public class SubAccountMarginDetail {

    private String email;

    private BigDecimal marginLevel;

    private BigDecimal totalAssetOfBtc;

    private BigDecimal totalLiabilityOfBtc;

    private BigDecimal totalNetAssetOfBtc;

    private MarginTradeCoeffVo marginTradeCoeffVo;

    private List<MarginUserAssetVo> marginUserAssetVoList;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public MarginTradeCoeffVo getMarginTradeCoeffVo() {
        return marginTradeCoeffVo;
    }

    public void setMarginTradeCoeffVo(MarginTradeCoeffVo marginTradeCoeffVo) {
        this.marginTradeCoeffVo = marginTradeCoeffVo;
    }

    public List<MarginUserAssetVo> getMarginUserAssetVoList() {
        return marginUserAssetVoList;
    }

    public void setMarginUserAssetVoList(List<MarginUserAssetVo> marginUserAssetVoList) {
        this.marginUserAssetVoList = marginUserAssetVoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("email", email)
                .append("marginLevel", marginLevel).append("totalAssetOfBtc", totalAssetOfBtc)
                .append("totalLiabilityOfBtc", totalLiabilityOfBtc).append("totalNetAssetOfBtc", totalNetAssetOfBtc)
                .append("marginTradeCoeffVo", marginTradeCoeffVo).append("marginUserAssetVoList", marginUserAssetVoList)
                .toString();
    }
}
