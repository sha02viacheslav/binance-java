package com.binance.client.model.event;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.binance.client.model.market.OrderBookEntry;

import java.util.List;

public class DiffDepthEvent {

    private String eventType;

    private Long eventTime;

    private String symbol;

    private Long firstUpdateId;

    private Long finalUpdateId;

    private List<OrderBookEntry> bids;

    private List<OrderBookEntry> asks;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getFirstUpdateId() {
        return firstUpdateId;
    }

    public void setFirstUpdateId(Long firstUpdateId) {
        this.firstUpdateId = firstUpdateId;
    }

    public Long getFinalUpdateId() {
        return finalUpdateId;
    }

    public void setFinalUpdateId(Long finalUpdateId) {
        this.finalUpdateId = finalUpdateId;
    }

    public List<OrderBookEntry> getBids() {
        return bids;
    }

    public void setBids(List<OrderBookEntry> bids) {
        this.bids = bids;
    }

    public List<OrderBookEntry> getAsks() {
        return asks;
    }

    public void setAsks(List<OrderBookEntry> asks) {
        this.asks = asks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("eventType", eventType)
                .append("eventTime", eventTime).append("symbol", symbol).append("firstUpdateId", firstUpdateId)
                .append("finalUpdateId", finalUpdateId).append("bids", bids).append("asks", asks).toString();
    }
}
