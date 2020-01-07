package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

public class ExchangeInfoEntry {

    private String symbol;

    private String status;

    private String baseAsset;

    private Long baseAssetPrecision;

    private String quoteAsset;

    private Long quotePrecision;

    private List<String> orderTypes;

    private Boolean icebergAllowed;

    private Boolean ocoAllowed;

    private Boolean isSpotTradingAllowed;

    private Boolean isMarginTradingAllowed;

    private List<List<Map<String, String>>> filters;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public Long getBaseAssetPrecision() {
        return baseAssetPrecision;
    }

    public void setBaseAssetPrecision(Long baseAssetPrecision) {
        this.baseAssetPrecision = baseAssetPrecision;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public void setQuoteAsset(String quoteAsset) {
        this.quoteAsset = quoteAsset;
    }

    public Long getQuotePrecision() {
        return quotePrecision;
    }

    public void setQuotePrecision(Long quotePrecision) {
        this.quotePrecision = quotePrecision;
    }

    public List<String> getOrderTypes() {
        return orderTypes;
    }

    public void setOrderTypes(List<String> orderTypes) {
        this.orderTypes = orderTypes;
    }

    public Boolean getIcebergAllowed() {
        return icebergAllowed;
    }

    public void setIcebergAllowed(Boolean icebergAllowed) {
        this.icebergAllowed = icebergAllowed;
    }

    public Boolean getOcoAllowed() {
        return ocoAllowed;
    }

    public void setOcoAllowed(Boolean ocoAllowed) {
        this.ocoAllowed = ocoAllowed;
    }

    public Boolean getIsSpotTradingAllowed() {
        return isSpotTradingAllowed;
    }

    public void setIsSpotTradingAllowed(Boolean isSpotTradingAllowed) {
        this.isSpotTradingAllowed = isSpotTradingAllowed;
    }

    public Boolean getIsMarginTradingAllowed() {
        return isMarginTradingAllowed;
    }

    public void setIsMarginTradingAllowed(Boolean isMarginTradingAllowed) {
        this.isMarginTradingAllowed = isMarginTradingAllowed;
    }

    public List<List<Map<String, String>>> getFilters() {
        return filters;
    }

    public void setFilters(List<List<Map<String, String>>> filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("status", status).append("baseAsset", baseAsset)
                .append("baseAssetPrecision", baseAssetPrecision).append("quoteAsset", quoteAsset)
                .append("quotePrecision", quotePrecision).append("orderTypes", orderTypes)
                .append("icebergAllowed", icebergAllowed).append("ocoAllowed", ocoAllowed)
                .append("isSpotTradingAllowed", isSpotTradingAllowed)
                .append("isMarginTradingAllowed", isMarginTradingAllowed).append("filters", filters).toString();
    }
}
