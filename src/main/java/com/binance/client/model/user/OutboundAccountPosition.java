package com.binance.client.model.user;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import com.binance.client.model.wallet.Balance;

public class OutboundAccountPosition {

    private Integer updateTime;

    private List<Balance> balances;

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("updateTime", updateTime)
                .append("balances", balances).toString();
    }
}
