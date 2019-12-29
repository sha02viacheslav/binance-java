package com.binance.client.model.event;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class SymbolTickerEvent {

    private String eventType;

    private Integer eventTime;

    private String symbol;

    private BigDecimal priceChange;

    private BigDecimal priceChangePercent;

    private BigDecimal weightedAvgPrice;

    private BigDecimal firstPrice;

    private BigDecimal lastPrice;

    private BigDecimal lastQty;

    private BigDecimal bestBidPrice;

    private BigDecimal bestBidQty;

    private BigDecimal bestAskPrice;

    private BigDecimal bestAskQty;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal totalTradedBaseAssetVolume;

    private BigDecimal totalTradedQuoteAssetVolume;

    private Integer openTime;

    private Integer closeTime;

    private Integer firstId;

    private Integer lastId;

    private Integer count;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getEventTime() {
        return eventTime;
    }

    public void setEventTime(Integer eventTime) {
        this.eventTime = eventTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(BigDecimal priceChange) {
        this.priceChange = priceChange;
    }

    public BigDecimal getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(BigDecimal priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public BigDecimal getWeightedAvgPrice() {
        return weightedAvgPrice;
    }

    public void setWeightedAvgPrice(BigDecimal weightedAvgPrice) {
        this.weightedAvgPrice = weightedAvgPrice;
    }

    public BigDecimal getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(BigDecimal firstPrice) {
        this.firstPrice = firstPrice;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getLastQty() {
        return lastQty;
    }

    public void setLastQty(BigDecimal lastQty) {
        this.lastQty = lastQty;
    }

    public BigDecimal getBestBidPrice() {
        return bestBidPrice;
    }

    public void setBestBidPrice(BigDecimal bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
    }

    public BigDecimal getBestBidQty() {
        return bestBidQty;
    }

    public void setBestBidQty(BigDecimal bestBidQty) {
        this.bestBidQty = bestBidQty;
    }

    public BigDecimal getBestAskPrice() {
        return bestAskPrice;
    }

    public void setBestAskPrice(BigDecimal bestAskPrice) {
        this.bestAskPrice = bestAskPrice;
    }

    public BigDecimal getBestAskQty() {
        return bestAskQty;
    }

    public void setBestAskQty(BigDecimal bestAskQty) {
        this.bestAskQty = bestAskQty;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getTotalTradedBaseAssetVolume() {
        return totalTradedBaseAssetVolume;
    }

    public void setTotalTradedBaseAssetVolume(BigDecimal totalTradedBaseAssetVolume) {
        this.totalTradedBaseAssetVolume = totalTradedBaseAssetVolume;
    }

    public BigDecimal getTotalTradedQuoteAssetVolume() {
        return totalTradedQuoteAssetVolume;
    }

    public void setTotalTradedQuoteAssetVolume(BigDecimal totalTradedQuoteAssetVolume) {
        this.totalTradedQuoteAssetVolume = totalTradedQuoteAssetVolume;
    }

    public Integer getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Integer openTime) {
        this.openTime = openTime;
    }

    public Integer getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Integer closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public Integer getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("eventType", eventType)
                .append("eventTime", eventTime).append("symbol", symbol).append("priceChange", priceChange)
                .append("priceChangePercent", priceChangePercent).append("weightedAvgPrice", weightedAvgPrice)
                .append("firstPrice", firstPrice).append("lastPrice", lastPrice).append("lastQty", lastQty)
                .append("bestBidPrice", bestBidPrice).append("bestBidQty", bestBidQty)
                .append("bestAskPrice", bestAskPrice).append("bestAskQty", bestAskQty).append("open", open)
                .append("high", high).append("low", low)
                .append("totalTradedBaseAssetVolume", totalTradedBaseAssetVolume)
                .append("totalTradedQuoteAssetVolume", totalTradedQuoteAssetVolume).append("openTime", openTime)
                .append("closeTime", closeTime).append("firstId", firstId).append("lastId", lastId)
                .append("count", count).toString();
    }
}
