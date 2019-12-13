package com.binance.client;

import com.binance.client.exception.HuobiApiException;

/**
 * The error handler for the subscription.
 */
@FunctionalInterface
public interface SubscriptionErrorHandler {

  void onError(HuobiApiException exception);
}
