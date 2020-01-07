package com.binance.client.model.wallet;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class DustTransferEntry {

    private BigDecimal amount;

    private String fromAsset;

    private Long operateTime;

    private BigDecimal serviceChargeAmount;

    private Long tranId;

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

    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }

    public BigDecimal getServiceChargeAmount() {
        return serviceChargeAmount;
    }

    public void setServiceChargeAmount(BigDecimal serviceChargeAmount) {
        this.serviceChargeAmount = serviceChargeAmount;
    }

    public Long getTranId() {
        return tranId;
    }

    public void setTranId(Long tranId) {
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
