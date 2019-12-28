package com.binance.client.impl;

import com.binance.client.impl.utils.JsonWrapper;

import com.binance.client.SubscriptionErrorHandler;
import com.binance.client.SubscriptionListener;
import com.binance.client.impl.utils.Channels;
import com.binance.client.model.enums.CandlestickInterval;
import com.binance.client.model.event.AggregateTradeEvent;
import com.binance.client.model.event.CandlestickEvent;
import com.binance.client.model.event.SymbolMiniTickerEvent;
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

    WebsocketRequest<CandlestickEvent> subscribeCandlestickEvent(String symbol, CandlestickInterval interval,
            SubscriptionListener<CandlestickEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(symbol, "symbol")
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<CandlestickEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***Candlestick for " + symbol + "***"; 
        request.connectionHandler = (connection) -> connection.send(Channels.candlestickChannel(symbol, interval));

        request.jsonParser = (jsonWrapper) -> {
            CandlestickEvent result = new CandlestickEvent();
            result.setEventType(jsonWrapper.getString("e"));
            result.setEventTime(jsonWrapper.getInteger("E"));
            result.setSymbol(jsonWrapper.getString("s"));
            JsonWrapper jsondata = jsonWrapper.getJsonObject("k");
            result.setStartTime(jsondata.getInteger("t"));
            result.setCloseTime(jsondata.getInteger("T"));
            result.setSymbol(jsondata.getString("s"));
            result.setInterval(jsondata.getString("i"));
            result.setFirstTradeId(jsondata.getInteger("f"));
            result.setLastTradeId(jsondata.getInteger("L"));
            result.setOpen(jsondata.getBigDecimal("o"));
            result.setClose(jsondata.getBigDecimal("c"));
            result.setHigh(jsondata.getBigDecimal("h"));
            result.setLow(jsondata.getBigDecimal("l"));
            result.setVolume(jsondata.getBigDecimal("v"));
            result.setNumTrades(jsondata.getInteger("n"));
            result.setIsClosed(jsondata.getBoolean("x"));
            result.setQuoteAssetVolume(jsondata.getBigDecimal("q"));
            result.setTakerBuyBaseAssetVolume(jsondata.getBigDecimal("V"));
            result.setTakerBuyQuoteAssetVolume(jsondata.getBigDecimal("Q"));
            result.setIgnore(jsondata.getInteger("B"));
            return result;
        };
        return request;
    }

    WebsocketRequest<SymbolMiniTickerEvent> subscribeSymbolMiniTickerEvent(String symbol,
            SubscriptionListener<SymbolMiniTickerEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(symbol, "symbol")
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<SymbolMiniTickerEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***Individual Symbol Mini Ticker for " + symbol + "***"; 
        request.connectionHandler = (connection) -> connection.send(Channels.miniTickerChannel(symbol));

        request.jsonParser = (jsonWrapper) -> {
            SymbolMiniTickerEvent result = new SymbolMiniTickerEvent();
            result.setEventType(jsonWrapper.getString("e"));
            result.setEventTime(jsonWrapper.getInteger("E"));
            result.setSymbol(jsonWrapper.getString("s"));
            result.setOpen(jsonWrapper.getBigDecimal("o"));
            result.setClose(jsonWrapper.getBigDecimal("c"));
            result.setHigh(jsonWrapper.getBigDecimal("h"));
            result.setLow(jsonWrapper.getBigDecimal("l"));
            result.setTotalTradedBaseAssetVolume(jsonWrapper.getBigDecimal("v"));
            result.setTotalTradedQuoteAssetVolume(jsonWrapper.getBigDecimal("q"));
            return result;
        };
        return request;
    }

}
