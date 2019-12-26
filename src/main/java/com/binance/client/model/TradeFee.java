package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class TradeFee {

    private String symbol;

    private BigDecimal maker;

    private BigDecimal taker;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getMaker() {
        return maker;
    }

    public void setMaker(BigDecimal maker) {
        this.maker = maker;
    }

    public BigDecimal getTaker() {
        return taker;
    }

    public void setTaker(BigDecimal taker) {
        this.taker = taker;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("maker", maker).append("taker", taker).toString();
    }
}
