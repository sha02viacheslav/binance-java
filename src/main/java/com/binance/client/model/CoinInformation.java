package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.math.BigDecimal;

import java.util.List;

public class CoinInformation {

    private String coin;

    private Boolean depositAllEnable;

    private BigDecimal free;

    private BigDecimal freeze;

    private BigDecimal ipoable;

    private BigDecimal ipoing;

    private Boolean isLegalMoney;

    private BigDecimal locked;

    private String name;

    private BigDecimal storage;

    private Boolean trading;

    private Boolean withdrawAllEnable;

    private BigDecimal withdrawing;

    private List<Network> networkList;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Boolean getDepositAllEnable() {
        return depositAllEnable;
    }

    public void setDepositAllEnable(Boolean depositAllEnable) {
        this.depositAllEnable = depositAllEnable;
    }

    public BigDecimal getFree() {
        return free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public BigDecimal getIpoable() {
        return ipoable;
    }

    public void setIpoable(BigDecimal ipoable) {
        this.ipoable = ipoable;
    }

    public BigDecimal getIpoing() {
        return ipoing;
    }

    public void setIpoing(BigDecimal ipoing) {
        this.ipoing = ipoing;
    }

    public Boolean getIsLegalMoney() {
        return isLegalMoney;
    }

    public void setIsLegalMoney(Boolean isLegalMoney) {
        this.isLegalMoney = isLegalMoney;
    }

    public BigDecimal getLocked() {
        return locked;
    }

    public void setLocked(BigDecimal locked) {
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getStorage() {
        return storage;
    }

    public void setStorage(BigDecimal storage) {
        this.storage = storage;
    }

    public Boolean getTrading() {
        return trading;
    }

    public void setTrading(Boolean trading) {
        this.trading = trading;
    }

    public Boolean getWithdrawAllEnable() {
        return withdrawAllEnable;
    }

    public void setWithdrawAllEnable(Boolean withdrawAllEnable) {
        this.withdrawAllEnable = withdrawAllEnable;
    }

    public BigDecimal getWithdrawing() {
        return withdrawing;
    }

    public void setWithdrawing(BigDecimal withdrawing) {
        this.withdrawing = withdrawing;
    }

    public List<Network> getNetworkList() {
        return networkList;
    }

    public void setNetworkList(List<Network> networkList) {
        this.networkList = networkList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("coin", coin)
                .append("depositAllEnable", depositAllEnable).append("free", free).append("freeze", freeze)
                .append("ipoable", ipoable).append("ipoing", ipoing).append("isLegalMoney", isLegalMoney)
                .append("locked", locked).append("name", name).append("storage", storage).append("trading", trading)
                .append("withdrawAllEnable", withdrawAllEnable).append("withdrawing", withdrawing)
                .append("networkList", networkList).toString();
    }
}
