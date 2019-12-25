package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AccountStatus {

    private String msg;

    private List<String> objs;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getObjs() {
        return objs;
    }

    public void setObjs(List<String> objs) {
        this.objs = objs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("msg", msg)
                .append("objs", objs).toString();
    }
}
