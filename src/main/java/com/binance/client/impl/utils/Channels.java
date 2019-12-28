package com.binance.client.impl.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.binance.client.model.enums.CandlestickInterval;

public abstract class Channels {

    public static final String OP_SUB = "sub";
    public static final String OP_REQ = "req";

    public static String aggregateTradeChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@aggTrade");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }
  
    public static String tradeChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@trade");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }
  
    public static String candlestickChannel(String symbol, CandlestickInterval interval) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@kline_" + interval);
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }
  
    public static String miniTickerChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@miniTicker");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }
  
}