package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class AggregateTrade {

    private Integer id;

    private BigDecimal price;

    private BigDecimal qty;

    private Integer firstId;

    private Integer lastId;

    private Integer time;

    private Boolean isBuyerMaker;

    private Boolean isBestMatch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public Integer getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Boolean getIsBuyerMaker() {
        return isBuyerMaker;
    }

    public void setIsBuyerMaker(Boolean isBuyerMaker) {
        this.isBuyerMaker = isBuyerMaker;
    }

    public Boolean getIsBestMatch() {
        return isBestMatch;
    }

    public void setIsBestMatch(Boolean isBestMatch) {
        this.isBestMatch = isBestMatch;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("id", id)
                .append("price", price).append("qty", qty).append("firstId", firstId).append("lastId", lastId)
                .append("time", time).append("isBuyerMaker", isBuyerMaker).append("isBestMatch", isBestMatch)
                .toString();
    }
}
