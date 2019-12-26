package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Indicator {

    private String symbol;

    private List<IndicatorInfo> informations;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<IndicatorInfo> getInformations() {
        return informations;
    }

    public void setInformations(List<IndicatorInfo> informations) {
        this.informations = informations;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("informations", informations).toString();
    }
}
