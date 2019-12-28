package com.binance.client;

import com.binance.client.impl.BinanceApiInternalFactory;
import com.binance.client.model.event.AggregateTradeEvent;

/***
 * The subscription client interface, it is used for subscribing any market data
 * update and account change, it is asynchronous, so you must implement the
 * SubscriptionListener interface. The server will push any update to the
 * client. if client get the update, the onReceive method will be called.
 */
public interface SubscriptionClient {
    /**
     * Create the subscription client to subscribe the update from server.
     *
     * @return The instance of synchronous client.
     */
    static SubscriptionClient create() {
        return create("", "", new SubscriptionOptions());
    }

    /**
     * Create the subscription client to subscribe the update from server.
     *
     * @param apiKey    The public key applied from Huobi.
     * @param secretKey The private key applied from Huobi.
     * @return The instance of synchronous client.
     */
    static SubscriptionClient create(String apiKey, String secretKey) {
        return BinanceApiInternalFactory.getInstance().createSubscriptionClient(apiKey, secretKey,
                new SubscriptionOptions());
    }

    /**
     * Create the subscription client to subscribe the update from server.
     *
     * @param apiKey              The public key applied from Huobi.
     * @param secretKey           The private key applied from Huobi.
     * @param subscriptionOptions The option of subscription connection, see
     *                            {@link SubscriptionOptions}
     * @return The instance of synchronous client.
     */
    static SubscriptionClient create(String apiKey, String secretKey, SubscriptionOptions subscriptionOptions) {
        return BinanceApiInternalFactory.getInstance().createSubscriptionClient(apiKey, secretKey, subscriptionOptions);
    }

    /**
     * Unsubscribe all subscription.
     */
    void unsubscribeAll();

    /**
     * Subscribe aggregate trade event. If the aggregate trade is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param symbol      The symbol, like "btcusdt".
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     *                     or error happen between client and Huobi server.
     */
    void subscribeAggregateTradeEvent(String symbol,
            SubscriptionListener<AggregateTradeEvent> callback, SubscriptionErrorHandler errorHandler);


}
