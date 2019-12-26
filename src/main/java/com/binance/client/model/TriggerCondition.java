package com.binance.client.model;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TriggerCondition {

    private String GCR;

    private String IFER;

    private String UFR;

    public String getGCR() {
        return GCR;
    }

    public void setGCR(String GCR) {
        this.GCR = GCR;
    }

    public String getIFER() {
        return IFER;
    }

    public void setIFER(String IFER) {
        this.IFER = IFER;
    }

    public String getUFR() {
        return UFR;
    }

    public void setUFR(String UFR) {
        this.UFR = UFR;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("GCR", GCR)
                .append("IFER", IFER).append("UFR", UFR).toString();
    }
}
