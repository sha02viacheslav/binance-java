package com.binance.client.model.event;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class TradeEvent {

    private String eventType;

    private Long eventTime;

    private String symbol;

    private Long id;

    private BigDecimal price;

    private BigDecimal qty;

    private Long buyerOrderId;

    private Long sellerOrderId;

    private Long time;

    private Boolean isBuyerMaker;

    private Boolean ignore;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Long getBuyerOrderId() {
        return buyerOrderId;
    }

    public void setBuyerOrderId(Long buyerOrderId) {
        this.buyerOrderId = buyerOrderId;
    }

    public Long getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(Long sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Boolean getIsBuyerMaker() {
        return isBuyerMaker;
    }

    public void setIsBuyerMaker(Boolean isBuyerMaker) {
        this.isBuyerMaker = isBuyerMaker;
    }

    public Boolean getIgnore() {
        return ignore;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("eventType", eventType)
                .append("eventTime", eventTime).append("symbol", symbol).append("id", id).append("price", price)
                .append("qty", qty).append("buyerOrderId", buyerOrderId).append("sellerOrderId", sellerOrderId)
                .append("time", time).append("isBuyerMaker", isBuyerMaker).append("ignore", ignore).toString();
    }
}
