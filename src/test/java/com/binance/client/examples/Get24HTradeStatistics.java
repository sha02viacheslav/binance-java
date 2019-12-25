package com.binance.client.examples;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

public class Get24HTradeStatistics {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create("", "", options);
        System.out.println(syncRequestClient.get24HTradeStatistics("XRPRUB"));
    }
}
