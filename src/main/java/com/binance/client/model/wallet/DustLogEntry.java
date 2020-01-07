package com.binance.client.model.wallet;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class DustLogEntry {

    private Long tranId;

    private BigDecimal serviceChargeAmount;

    private Long uid;

    private BigDecimal amount;

    private String operateTime;

    private BigDecimal transferedAmount;

    private String fromAsset;

    public Long getTranId() {
        return tranId;
    }

    public void setTranId(Long tranId) {
        this.tranId = tranId;
    }

    public BigDecimal getServiceChargeAmount() {
        return serviceChargeAmount;
    }

    public void setServiceChargeAmount(BigDecimal serviceChargeAmount) {
        this.serviceChargeAmount = serviceChargeAmount;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public BigDecimal getTransferedAmount() {
        return transferedAmount;
    }

    public void setTransferedAmount(BigDecimal transferedAmount) {
        this.transferedAmount = transferedAmount;
    }

    public String getFromAsset() {
        return fromAsset;
    }

    public void setFromAsset(String fromAsset) {
        this.fromAsset = fromAsset;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("tranId", tranId)
                .append("serviceChargeAmount", serviceChargeAmount).append("uid", uid).append("amount", amount)
                .append("operateTime", operateTime).append("transferedAmount", transferedAmount)
                .append("fromAsset", fromAsset).toString();
    }
}
