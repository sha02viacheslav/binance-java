package com.binance.client.model.spot;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class Order {

    private String symbol;

    private Integer orderId;

    private Integer orderListId;

    private String clientOrderId;

    private BigDecimal price;

    private BigDecimal origQty;

    private BigDecimal executedQty;

    private BigDecimal cummulativeQuoteQty;

    private String status;

    private String timeInForce;

    private String type;

    private String side;

    private BigDecimal stopPrice;

    private BigDecimal icebergQty;

    private Integer time;

    private Integer updateTime;

    private Boolean isWorking;

    private BigDecimal origQuoteOrderQty;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(Integer orderListId) {
        this.orderListId = orderListId;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOrigQty() {
        return origQty;
    }

    public void setOrigQty(BigDecimal origQty) {
        this.origQty = origQty;
    }

    public BigDecimal getExecutedQty() {
        return executedQty;
    }

    public void setExecutedQty(BigDecimal executedQty) {
        this.executedQty = executedQty;
    }

    public BigDecimal getCummulativeQuoteQty() {
        return cummulativeQuoteQty;
    }

    public void setCummulativeQuoteQty(BigDecimal cummulativeQuoteQty) {
        this.cummulativeQuoteQty = cummulativeQuoteQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public BigDecimal getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(BigDecimal stopPrice) {
        this.stopPrice = stopPrice;
    }

    public BigDecimal getIcebergQty() {
        return icebergQty;
    }

    public void setIcebergQty(BigDecimal icebergQty) {
        this.icebergQty = icebergQty;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(Boolean isWorking) {
        this.isWorking = isWorking;
    }

    public BigDecimal getOrigQuoteOrderQty() {
        return origQuoteOrderQty;
    }

    public void setOrigQuoteOrderQty(BigDecimal origQuoteOrderQty) {
        this.origQuoteOrderQty = origQuoteOrderQty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("orderId", orderId).append("orderListId", orderListId).append("clientOrderId", clientOrderId)
                .append("price", price).append("origQty", origQty).append("executedQty", executedQty)
                .append("cummulativeQuoteQty", cummulativeQuoteQty).append("status", status)
                .append("timeInForce", timeInForce).append("type", type).append("side", side)
                .append("stopPrice", stopPrice).append("icebergQty", icebergQty).append("time", time)
                .append("updateTime", updateTime).append("isWorking", isWorking)
                .append("origQuoteOrderQty", origQuoteOrderQty).toString();
    }
}
