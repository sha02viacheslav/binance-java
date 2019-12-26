package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

public class DustTransfer {

    private BigDecimal totalServiceCharge;

    private BigDecimal totalTransfered;

    private List<DustTransferEntry> transferResult;

    public BigDecimal getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(BigDecimal totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public BigDecimal getTotalTransfered() {
        return totalTransfered;
    }

    public void setTotalTransfered(BigDecimal totalTransfered) {
        this.totalTransfered = totalTransfered;
    }

    public List<DustTransferEntry> getTransferResult() {
        return transferResult;
    }

    public void setTransferResult(List<DustTransferEntry> transferResult) {
        this.transferResult = transferResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("totalServiceCharge", totalServiceCharge).append("totalTransfered", totalTransfered)
                .append("transferResult", transferResult).toString();
    }
}
