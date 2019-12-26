package com.binance.client.model.wallet;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class MarginTradeCoeffVo {

    private BigDecimal forceLiquidationBar;

    private BigDecimal marginCallBar;

    private BigDecimal normalBar;

    public BigDecimal getForceLiquidationBar() {
        return forceLiquidationBar;
    }

    public void setForceLiquidationBar(BigDecimal forceLiquidationBar) {
        this.forceLiquidationBar = forceLiquidationBar;
    }

    public BigDecimal getMarginCallBar() {
        return marginCallBar;
    }

    public void setMarginCallBar(BigDecimal marginCallBar) {
        this.marginCallBar = marginCallBar;
    }

    public BigDecimal getNormalBar() {
        return normalBar;
    }

    public void setNormalBar(BigDecimal normalBar) {
        this.normalBar = normalBar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("forceLiquidationBar", forceLiquidationBar).append("marginCallBar", marginCallBar)
                .append("normalBar", normalBar).toString();
    }
}
