package com.binance.client.model.margin;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.binance.client.model.spot.Fill;

import java.math.BigDecimal;
import java.util.List;

public class MarginNewOrder {

    private String symbol;

    private Long orderId;

    private String clientOrderId;

    private Long transactTime;

    private BigDecimal price;

    private BigDecimal origQty;

    private BigDecimal executedQty;

    private BigDecimal cummulativeQuoteQty;

    private String status;

    private String timeInForce;

    private String type;

    private String side;

    private String marginBuyBorrowAmount;

    private String marginBuyBorrowAsset;

    private List<Fill> fills;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public Long getTransactTime() {
        return transactTime;
    }

    public void setTransactTime(Long transactTime) {
        this.transactTime = transactTime;
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

    public String getMarginBuyBorrowAmount() {
        return marginBuyBorrowAmount;
    }

    public void setMarginBuyBorrowAmount(String marginBuyBorrowAmount) {
        this.marginBuyBorrowAmount = marginBuyBorrowAmount;
    }

    public String getMarginBuyBorrowAsset() {
        return marginBuyBorrowAsset;
    }

    public void setMarginBuyBorrowAsset(String marginBuyBorrowAsset) {
        this.marginBuyBorrowAsset = marginBuyBorrowAsset;
    }

    public List<Fill> getFills() {
        return fills;
    }

    public void setFills(List<Fill> fills) {
        this.fills = fills;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("orderId", orderId).append("clientOrderId", clientOrderId)
                .append("transactTime", transactTime).append("price", price).append("origQty", origQty)
                .append("executedQty", executedQty).append("cummulativeQuoteQty", cummulativeQuoteQty)
                .append("status", status).append("timeInForce", timeInForce).append("type", type).append("side", side)
                .append("marginBuyBorrowAmount", marginBuyBorrowAmount)
                .append("marginBuyBorrowAsset", marginBuyBorrowAsset).append("fills", fills).toString();
    }
}
