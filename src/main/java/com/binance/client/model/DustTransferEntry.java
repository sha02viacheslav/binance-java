package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class DustTransferEntry {

    private BigDecimal amount;

    private String fromAsset;

    private Integer operateTime;

    private BigDecimal serviceChargeAmount;

    private Integer tranId;

    private BigDecimal transferedAmount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFromAsset() {
        return fromAsset;
    }

    public void setFromAsset(String fromAsset) {
        this.fromAsset = fromAsset;
    }

    public Integer getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Integer operateTime) {
        this.operateTime = operateTime;
    }

    public BigDecimal getServiceChargeAmount() {
        return serviceChargeAmount;
    }

    public void setServiceChargeAmount(BigDecimal serviceChargeAmount) {
        this.serviceChargeAmount = serviceChargeAmount;
    }

    public Integer getTranId() {
        return tranId;
    }

    public void setTranId(Integer tranId) {
        this.tranId = tranId;
    }

    public BigDecimal getTransferedAmount() {
        return transferedAmount;
    }

    public void setTransferedAmount(BigDecimal transferedAmount) {
        this.transferedAmount = transferedAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("amount", amount)
                .append("fromAsset", fromAsset).append("operateTime", operateTime)
                .append("serviceChargeAmount", serviceChargeAmount).append("tranId", tranId)
                .append("transferedAmount", transferedAmount).toString();
    }
}
