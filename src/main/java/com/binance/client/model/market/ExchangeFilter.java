package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExchangeFilter {

    private String filterType;

    private Integer maxNumOrders;

    private Integer maxNumAlgoOrders;

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public Integer getMaxNumOrders() {
        return maxNumOrders;
    }

    public void setMaxNumOrders(Integer maxNumOrders) {
        this.maxNumOrders = maxNumOrders;
    }

    public Integer getMaxNumAlgoOrders() {
        return maxNumAlgoOrders;
    }

    public void setMaxNumAlgoOrders(Integer maxNumAlgoOrders) {
        this.maxNumAlgoOrders = maxNumAlgoOrders;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("filterType", filterType)
                .append("maxNumOrders", maxNumOrders).append("maxNumAlgoOrders", maxNumAlgoOrders).toString();
    }
}
