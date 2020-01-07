package com.binance.client.model.margin;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MarginPair {

    private Long id;

    private String symbol;

    private String base;

    private String quote;

    private Boolean isMarginTrade;

    private Boolean isBuyAllowed;

    private Boolean isSellAllowed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Boolean getIsMarginTrade() {
        return isMarginTrade;
    }

    public void setIsMarginTrade(Boolean isMarginTrade) {
        this.isMarginTrade = isMarginTrade;
    }

    public Boolean getIsBuyAllowed() {
        return isBuyAllowed;
    }

    public void setIsBuyAllowed(Boolean isBuyAllowed) {
        this.isBuyAllowed = isBuyAllowed;
    }

    public Boolean getIsSellAllowed() {
        return isSellAllowed;
    }

    public void setIsSellAllowed(Boolean isSellAllowed) {
        this.isSellAllowed = isSellAllowed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("id", id)
                .append("symbol", symbol).append("base", base).append("quote", quote)
                .append("isMarginTrade", isMarginTrade).append("isBuyAllowed", isBuyAllowed)
                .append("isSellAllowed", isSellAllowed).toString();
    }
}
