package com.binance.client.examples.spot;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;

public class GetAllOrders {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println(syncRequestClient.getAllOrders("RENBTC", null, null, null, 10));
        // System.out.println(syncRequestClient.getAllOrders("RENBTC", null, null, null, null));
    }
}