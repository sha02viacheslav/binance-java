package com.binance.client.model.margin;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class MarginPriceIndex {

    private Long calcTime;

    private BigDecimal price;

    private String symbol;

    public Long getCalcTime() {
        return calcTime;
    }

    public void setCalcTime(Long calcTime) {
        this.calcTime = calcTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("calcTime", calcTime)
                .append("price", price).append("symbol", symbol).toString();
    }
}
