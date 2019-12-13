package com.binance.client.model.event;

import com.binance.client.model.TradeClearing;

public class TradeClearingEvent {

  private String ch;

  private TradeClearing data;

  public String getCh() {
    return ch;
  }

  public void setCh(String ch) {
    this.ch = ch;
  }

  public TradeClearing getData() {
    return data;
  }

  public void setData(TradeClearing data) {
    this.data = data;
  }
}
