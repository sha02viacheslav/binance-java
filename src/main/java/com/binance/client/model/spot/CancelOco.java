package com.binance.client.model.spot;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

public class CancelOco {

    private Integer orderListId;

    private String contingencyType;

    private String listStatusType;

    private String listOrderStatus;

    private String listClientOrderId;

    private Integer transactionTime;

    private String symbol;

    private List<OcoOrder> orders;

    private List<CancelOcoReport> orderReports;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<OcoOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<OcoOrder> orders) {
        this.orders = orders;
    }

    public List<CancelOcoReport> getOrderReports() {
        return orderReports;
    }

    public void setOrderReports(List<CancelOcoReport> orderReports) {
        this.orderReports = orderReports;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("orderListId", orderListId)
                .append("contingencyType", contingencyType).append("listStatusType", listStatusType)
                .append("listOrderStatus", listOrderStatus).append("listClientOrderId", listClientOrderId)
                .append("transactionTime", transactionTime).append("symbol", symbol).append("orders", orders)
                .append("orderReports", orderReports).toString();
    }
}
