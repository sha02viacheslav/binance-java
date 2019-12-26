package com.binance.client.model.wallet;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AccountApiTradingStatus {

    private Boolean isLocked;

    private Integer plannedRecoverTime;

    private Integer updateTime;

    private TriggerCondition triggerCondition;

    private List<Indicator> indicators;

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Integer getPlannedRecoverTime() {
        return plannedRecoverTime;
    }

    public void setPlannedRecoverTime(Integer plannedRecoverTime) {
        this.plannedRecoverTime = plannedRecoverTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public TriggerCondition getTriggerCondition() {
        return triggerCondition;
    }

    public void setTriggerCondition(TriggerCondition triggerCondition) {
        this.triggerCondition = triggerCondition;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("isLocked", isLocked)
                .append("plannedRecoverTime", plannedRecoverTime).append("updateTime", updateTime)
                .append("triggerCondition", triggerCondition).append("indicators", indicators).toString();
    }
}
