package com.binance.client.model.user;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ListStatus {

    private String symbol;

    private Integer orderListId;

    private String contingencyType;

    private String listStatusType;

    private String listOrderStatus;

    private String listRejectReason;

    private String listClientOrderId;

    private Integer transactionTime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(Integer orderListId) {
        this.orderListId = orderListId;
    }

    public String getContingencyType() {
        return contingencyType;
    }

    public void setContingencyType(String contingencyType) {
        this.contingencyType = contingencyType;
    }

    public String getListStatusType() {
        return listStatusType;
    }

    public void setListStatusType(String listStatusType) {
        this.listStatusType = listStatusType;
    }

    public String getListOrderStatus() {
        return listOrderStatus;
    }

    public void setListOrderStatus(String listOrderStatus) {
        this.listOrderStatus = listOrderStatus;
    }

    public String getListRejectReason() {
        return listRejectReason;
    }

    public void setListRejectReason(String listRejectReason) {
        this.listRejectReason = listRejectReason;
    }

    public String getListClientOrderId() {
        return listClientOrderId;
    }

    public void setListClientOrderId(String listClientOrderId) {
        this.listClientOrderId = listClientOrderId;
    }

    public Integer getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Integer transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("orderListId", orderListId).append("contingencyType", contingencyType)
                .append("listStatusType", listStatusType).append("listOrderStatus", listOrderStatus)
                .append("listRejectReason", listRejectReason).append("listClientOrderId", listClientOrderId)
                .append("transactionTime", transactionTime).toString();
    }
}
