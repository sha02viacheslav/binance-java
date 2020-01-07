package com.binance.client.model.user;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDataUpdateEvent {

    private String eventType;

    private Long eventTime;

    private OutboundAccountInfo outboundAccountInfo;

    private OutboundAccountPosition outboundAccountPosition;

    private BalanceUpdate balanceUpdate;

    private ExecutionReport executionReport;

    private ListStatus listStatus;

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

    public OutboundAccountInfo getOutboundAccountInfo() {
        return outboundAccountInfo;
    }

    public void setOutboundAccountInfo(OutboundAccountInfo outboundAccountInfo) {
        this.outboundAccountInfo = outboundAccountInfo;
    }

    public OutboundAccountPosition getOutboundAccountPosition() {
        return outboundAccountPosition;
    }

    public void setOutboundAccountPosition(OutboundAccountPosition outboundAccountPosition) {
        this.outboundAccountPosition = outboundAccountPosition;
    }

    public BalanceUpdate getBalanceUpdate() {
        return balanceUpdate;
    }

    public void setBalanceUpdate(BalanceUpdate balanceUpdate) {
        this.balanceUpdate = balanceUpdate;
    }

    public ExecutionReport getExecutionReport() {
        return executionReport;
    }

    public void setExecutionReport(ExecutionReport executionReport) {
        this.executionReport = executionReport;
    }

    public ListStatus getListStatus() {
        return listStatus;
    }

    public void setListStatus(ListStatus listStatus) {
        this.listStatus = listStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("eventType", eventType)
                .append("eventTime", eventTime).append("outboundAccountInfo", outboundAccountInfo)
                .append("outboundAccountPosition", outboundAccountPosition).append("balanceUpdate", balanceUpdate)
                .append("executionReport", executionReport).append("listStatus", listStatus).toString();
    }
}
