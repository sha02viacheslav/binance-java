package com.binance.client.model.user;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class ExecutionReport {

    private String symbol;

    private String clientOrderId;

    private String side;

    private String type;

    private String timeInForce;

    private BigDecimal orderQty;

    private BigDecimal orderPrice;

    private BigDecimal stopPrice;

    private BigDecimal icebergQty;

    private Long orderListId;

    private String origClientOrderId;

    private String executionType;

    private String orderStatus;

    private String errorCode;

    private Long orderId;

    private BigDecimal lastExecutedQty;

    private BigDecimal cumulativeFilledQty;

    private BigDecimal lastExecutedPrice;

    private Long commissionAmount;

    private String commissionAsset;

    private Long transactionTime;

    private Long tradeID;

    private Long ignore;

    private Boolean isOrderBook;

    private Boolean isMarkerSide;

    private Boolean isIgnore;

    private Long orderCreationTime;

    private BigDecimal cumulativeQuoteAssetQty;

    private BigDecimal lastQuoteAssetQty;

    private BigDecimal quoteOrderQty;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
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

    public Long getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(Long orderListId) {
        this.orderListId = orderListId;
    }

    public String getOrigClientOrderId() {
        return origClientOrderId;
    }

    public void setOrigClientOrderId(String origClientOrderId) {
        this.origClientOrderId = origClientOrderId;
    }

    public String getExecutionType() {
        return executionType;
    }

    public void setExecutionType(String executionType) {
        this.executionType = executionType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getLastExecutedQty() {
        return lastExecutedQty;
    }

    public void setLastExecutedQty(BigDecimal lastExecutedQty) {
        this.lastExecutedQty = lastExecutedQty;
    }

    public BigDecimal getCumulativeFilledQty() {
        return cumulativeFilledQty;
    }

    public void setCumulativeFilledQty(BigDecimal cumulativeFilledQty) {
        this.cumulativeFilledQty = cumulativeFilledQty;
    }

    public BigDecimal getLastExecutedPrice() {
        return lastExecutedPrice;
    }

    public void setLastExecutedPrice(BigDecimal lastExecutedPrice) {
        this.lastExecutedPrice = lastExecutedPrice;
    }

    public Long getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Long commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getCommissionAsset() {
        return commissionAsset;
    }

    public void setCommissionAsset(String commissionAsset) {
        this.commissionAsset = commissionAsset;
    }

    public Long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Long getTradeID() {
        return tradeID;
    }

    public void setTradeID(Long tradeID) {
        this.tradeID = tradeID;
    }

    public Long getIgnore() {
        return ignore;
    }

    public void setIgnore(Long ignore) {
        this.ignore = ignore;
    }

    public Boolean getIsOrderBook() {
        return isOrderBook;
    }

    public void setIsOrderBook(Boolean isOrderBook) {
        this.isOrderBook = isOrderBook;
    }

    public Boolean getIsMarkerSide() {
        return isMarkerSide;
    }

    public void setIsMarkerSide(Boolean isMarkerSide) {
        this.isMarkerSide = isMarkerSide;
    }

    public Boolean getIsIgnore() {
        return isIgnore;
    }

    public void setIsIgnore(Boolean isIgnore) {
        this.isIgnore = isIgnore;
    }

    public Long getOrderCreationTime() {
        return orderCreationTime;
    }

    public void setOrderCreationTime(Long orderCreationTime) {
        this.orderCreationTime = orderCreationTime;
    }

    public BigDecimal getCumulativeQuoteAssetQty() {
        return cumulativeQuoteAssetQty;
    }

    public void setCumulativeQuoteAssetQty(BigDecimal cumulativeQuoteAssetQty) {
        this.cumulativeQuoteAssetQty = cumulativeQuoteAssetQty;
    }

    public BigDecimal getLastQuoteAssetQty() {
        return lastQuoteAssetQty;
    }

    public void setLastQuoteAssetQty(BigDecimal lastQuoteAssetQty) {
        this.lastQuoteAssetQty = lastQuoteAssetQty;
    }

    public BigDecimal getQuoteOrderQty() {
        return quoteOrderQty;
    }

    public void setQuoteOrderQty(BigDecimal quoteOrderQty) {
        this.quoteOrderQty = quoteOrderQty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("clientOrderId", clientOrderId).append("side", side).append("type", type)
                .append("timeInForce", timeInForce).append("orderQty", orderQty).append("orderPrice", orderPrice)
                .append("stopPrice", stopPrice).append("icebergQty", icebergQty).append("orderListId", orderListId)
                .append("origClientOrderId", origClientOrderId).append("executionType", executionType)
                .append("orderStatus", orderStatus).append("errorCode", errorCode).append("orderId", orderId)
                .append("lastExecutedQty", lastExecutedQty).append("cumulativeFilledQty", cumulativeFilledQty)
                .append("lastExecutedPrice", lastExecutedPrice).append("commissionAmount", commissionAmount)
                .append("commissionAsset", commissionAsset).append("transactionTime", transactionTime)
                .append("tradeID", tradeID).append("ignore", ignore).append("isOrderBook", isOrderBook)
                .append("isMarkerSide", isMarkerSide).append("isIgnore", isIgnore)
                .append("orderCreationTime", orderCreationTime)
                .append("cumulativeQuoteAssetQty", cumulativeQuoteAssetQty)
                .append("lastQuoteAssetQty", lastQuoteAssetQty).append("quoteOrderQty", quoteOrderQty).toString();
    }
}
