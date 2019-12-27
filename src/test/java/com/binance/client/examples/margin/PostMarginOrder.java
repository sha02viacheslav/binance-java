package com.binance.client.examples.margin;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.enums.*;

public class PostMarginOrder {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println(syncRequestClient.postMarginOrder("BTCUSDT", OrderSide.SELL, OrderType.MARKET, "0.001",
                null, null, null, null, null, null, null, null));
    }
}