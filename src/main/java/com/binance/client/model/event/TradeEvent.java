package com.binance.client.model.event;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class TradeEvent {

    private String eventType;

    private Integer eventTime;

    private String symbol;

    private Integer id;

    private BigDecimal price;

    private BigDecimal qty;

    private Integer buyerOrderId;

    private Integer sellerOrderId;

    private Integer time;

    private Boolean isBuyerMaker;

    private Boolean ignore;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getBuyerOrderId() {
        return buyerOrderId;
    }

    public void setBuyerOrderId(Integer buyerOrderId) {
        this.buyerOrderId = buyerOrderId;
    }

    public Integer getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(Integer sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
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
