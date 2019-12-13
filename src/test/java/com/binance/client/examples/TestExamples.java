package com.binance.client.examples;

import com.binance.client.SubscriptionClient;
import com.binance.client.SyncRequestClient;
import com.binance.client.examples.constants.Constants;
import com.binance.client.model.enums.AccountType;
import com.binance.client.model.enums.BalanceMode;
import com.binance.client.model.enums.CandlestickInterval;
import com.binance.client.model.enums.OrderState;
import com.binance.client.model.request.AccountHistoryRequest;
import com.binance.client.model.request.CandlestickRequest;
import com.binance.client.model.request.CrossMarginLoanOrderRequest;
import com.binance.client.model.request.LoanOrderRequest;
import com.binance.client.model.request.OpenOrderRequest;
import com.binance.client.model.request.OrdersRequest;

public class TestExamples {

  public static void main(String[] args) {
    String symbol = "htusdt,btcusdt";

    SubscriptionClient subscriptionClient = SubscriptionClient.create(Constants.API_KEY, Constants.SECRET_KEY);

    subscriptionClient.requestAccountListEvent(event->{

    });


  }

}
