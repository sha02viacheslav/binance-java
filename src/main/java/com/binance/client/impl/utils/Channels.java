package com.binance.client.impl.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
  
}