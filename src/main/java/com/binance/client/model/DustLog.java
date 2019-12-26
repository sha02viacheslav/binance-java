package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.math.BigDecimal;

public class DustLog {

    private BigDecimal transferedTotal;

    private BigDecimal serviceChargeTotal;

    private Integer tranId;

    private String operateTime;

    private List<DustLogEntry> logs;

    public BigDecimal getTransferedTotal() {
        return transferedTotal;
    }

    public void setTransferedTotal(BigDecimal transferedTotal) {
        this.transferedTotal = transferedTotal;
    }

    public BigDecimal getServiceChargeTotal() {
        return serviceChargeTotal;
    }

    public void setServiceChargeTotal(BigDecimal serviceChargeTotal) {
        this.serviceChargeTotal = serviceChargeTotal;
    }

    public Integer getTranId() {
        return tranId;
    }

    public void setTranId(Integer tranId) {
        this.tranId = tranId;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public List<DustLogEntry> getLogs() {
        return logs;
    }

    public void setLogs(List<DustLogEntry> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("transferedTotal", transferedTotal).append("serviceChargeTotal", serviceChargeTotal)
                .append("tranId", tranId).append("operateTime", operateTime).append("logs", logs).toString();
    }
}
