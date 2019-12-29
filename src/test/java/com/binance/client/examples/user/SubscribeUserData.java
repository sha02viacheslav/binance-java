package com.binance.client.examples.user;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.SubscriptionClient;
import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.enums.AccountType;

public class SubscribeUserData {

    public static void main(String[] args) {

        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);

        // Start user data stream
        String listenKey = syncRequestClient.startUserDataStream(AccountType.SPOT);
        // System.out.println(syncRequestClient.startUserDataStream(AccountType.MARGIN));
        System.out.println("listenKey: " + listenKey);

        // Keep user data stream
        syncRequestClient.keepUserDataStream(AccountType.SPOT, listenKey);
        // syncRequestClient.keepUserDataStream(AccountType.MARGIN, listenKey);

        // Close user data stream
        syncRequestClient.closeUserDataStream(AccountType.SPOT, listenKey);
        // syncRequestClient.closeUserDataStream(AccountType.MARGIN, listenKey);

        SubscriptionClient client = SubscriptionClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

   
        client.subscribeUserDataEvent(listenKey, ((event) -> {
            System.out.println(event);
        }), null);

    }

}