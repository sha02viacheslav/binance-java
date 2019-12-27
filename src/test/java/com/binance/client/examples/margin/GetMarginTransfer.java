package com.binance.client.examples.margin;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;

import com.binance.client.model.enums.*;

public class GetMarginTransfer {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println(syncRequestClient.getMarginTransfer("BTC", TransferType.ROLL_IN, null, null, null, null));
    }
}