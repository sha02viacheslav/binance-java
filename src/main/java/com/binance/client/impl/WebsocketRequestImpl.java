package com.binance.client.impl;

import com.binance.client.SubscriptionErrorHandler;
import com.binance.client.SubscriptionListener;
import com.binance.client.impl.utils.Channels;
import com.binance.client.model.event.AggregateTradeEvent;
import com.binance.client.model.event.TradeEvent;

class WebsocketRequestImpl {

    WebsocketRequestImpl() {
    }

    WebsocketRequest<AggregateTradeEvent> subscribeAggregateTradeEvent(String symbol,
            SubscriptionListener<AggregateTradeEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(symbol, "symbol")
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<AggregateTradeEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***Aggregate Trade for " + symbol + "***"; 
        request.connectionHandler = (connection) -> connection.send(Channels.aggregateTradeChannel(symbol));

        request.jsonParser = (jsonWrapper) -> {
            AggregateTradeEvent result = new AggregateTradeEvent();
            result.setEventType(jsonWrapper.getString("e"));
            result.setEventTime(jsonWrapper.getInteger("E"));
            result.setSymbol(jsonWrapper.getString("s"));
            result.setId(jsonWrapper.getInteger("a"));
            result.setPrice(jsonWrapper.getBigDecimal("p"));
            result.setQty(jsonWrapper.getBigDecimal("q"));
            result.setFirstId(jsonWrapper.getInteger("f"));
            result.setLastId(jsonWrapper.getInteger("l"));
            result.setTime(jsonWrapper.getInteger("T"));
            result.setIsBuyerMaker(jsonWrapper.getBoolean("m"));
            result.setIgnore(jsonWrapper.getBoolean("M"));
            return result;
        };
        return request;
    }

    WebsocketRequest<TradeEvent> subscribeTradeEvent(String symbol,
            SubscriptionListener<TradeEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(symbol, "symbol")
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<TradeEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***Trade for " + symbol + "***"; 
        request.connectionHandler = (connection) -> connection.send(Channels.tradeChannel(symbol));

        request.jsonParser = (jsonWrapper) -> {
            TradeEvent result = new TradeEvent();
            result.setEventType(jsonWrapper.getString("e"));
            result.setEventTime(jsonWrapper.getInteger("E"));
            result.setSymbol(jsonWrapper.getString("s"));
            result.setId(jsonWrapper.getInteger("t"));
            result.setPrice(jsonWrapper.getBigDecimal("p"));
            result.setQty(jsonWrapper.getBigDecimal("q"));
            result.setBuyerOrderId(jsonWrapper.getInteger("b"));
            result.setSellerOrderId(jsonWrapper.getInteger("a"));
            result.setTime(jsonWrapper.getInteger("T"));
            result.setIsBuyerMaker(jsonWrapper.getBoolean("m"));
            result.setIgnore(jsonWrapper.getBoolean("M"));
            return result;
        };
        return request;
    }

}
