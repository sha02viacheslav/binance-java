package com.binance.client.model.spot;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.binance.client.model.wallet.Balance;

import java.util.List;

public class AccountInformation {

    private Integer makerCommission;

    private Integer takerCommission;

    private Integer buyerCommission;

    private Integer sellerCommission;

    private Boolean canTrade;

    private Boolean canWithdraw;

    private Boolean canDeposit;

    private Integer updateTime;

    private String accountType;

    private List<Balance> balances;

    public Integer getMakerCommission() {
        return makerCommission;
    }

    public void setMakerCommission(Integer makerCommission) {
        this.makerCommission = makerCommission;
    }

    public Integer getTakerCommission() {
        return takerCommission;
    }

    public void setTakerCommission(Integer takerCommission) {
        this.takerCommission = takerCommission;
    }

    public Integer getBuyerCommission() {
        return buyerCommission;
    }

    public void setBuyerCommission(Integer buyerCommission) {
        this.buyerCommission = buyerCommission;
    }

    public Integer getSellerCommission() {
        return sellerCommission;
    }

    public void setSellerCommission(Integer sellerCommission) {
        this.sellerCommission = sellerCommission;
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

    public Boolean getCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(Boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("makerCommission", makerCommission).append("takerCommission", takerCommission)
                .append("buyerCommission", buyerCommission).append("sellerCommission", sellerCommission)
                .append("canTrade", canTrade).append("canWithdraw", canWithdraw).append("canDeposit", canDeposit)
                .append("updateTime", updateTime).append("accountType", accountType).append("balances", balances)
                .toString();
    }
}
