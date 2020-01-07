package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class AveragePrice {

    private Long mins;

    private BigDecimal price;

    public Long getMins() {
        return mins;
    }

    public void setMins(Long mins) {
        this.mins = mins;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("mins", mins)
                .append("price", price).toString();
    }
}
