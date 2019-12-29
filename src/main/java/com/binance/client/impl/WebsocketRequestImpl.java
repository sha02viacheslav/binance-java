package com.binance.client.impl;

import java.util.LinkedList;
import java.util.List;

import com.binance.client.impl.utils.JsonWrapper;
import com.binance.client.impl.utils.JsonWrapperArray;

import com.binance.client.SubscriptionErrorHandler;
import com.binance.client.SubscriptionListener;
import com.binance.client.impl.utils.Channels;
import com.binance.client.model.enums.CandlestickInterval;
import com.binance.client.model.event.AggregateTradeEvent;
import com.binance.client.model.event.CandlestickEvent;
import com.binance.client.model.event.SymbolMiniTickerEvent;
import com.binance.client.model.event.SymbolTickerEvent;
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

    WebsocketRequest<SymbolMiniTickerEvent> subscribeAllMiniTickerEvent(
            SubscriptionListener<SymbolMiniTickerEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<SymbolMiniTickerEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***All Market Mini Tickers"; 
        request.connectionHandler = (connection) -> connection.send(Channels.miniTickerChannel());

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

    WebsocketRequest<SymbolTickerEvent> subscribeSymbolTickerEvent(String symbol,
            SubscriptionListener<SymbolTickerEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(symbol, "symbol")
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<SymbolTickerEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***Individual Symbol Ticker for " + symbol + "***"; 
        request.connectionHandler = (connection) -> connection.send(Channels.tickerChannel(symbol));

        request.jsonParser = (jsonWrapper) -> {
            SymbolTickerEvent result = new SymbolTickerEvent();
            result.setEventType(jsonWrapper.getString("e"));
            result.setEventTime(jsonWrapper.getInteger("E"));
            result.setSymbol(jsonWrapper.getString("s"));
            result.setPriceChange(jsonWrapper.getBigDecimal("p"));
            result.setPriceChangePercent(jsonWrapper.getBigDecimal("P"));
            result.setWeightedAvgPrice(jsonWrapper.getBigDecimal("w"));
            result.setFirstPrice(jsonWrapper.getBigDecimal("x"));
            result.setLastPrice(jsonWrapper.getBigDecimal("c"));
            result.setLastQty(jsonWrapper.getBigDecimal("Q"));
            result.setBestBidPrice(jsonWrapper.getBigDecimal("b"));
            result.setBestBidQty(jsonWrapper.getBigDecimal("B"));
            result.setBestAskPrice(jsonWrapper.getBigDecimal("a"));
            result.setBestAskQty(jsonWrapper.getBigDecimal("A"));
            result.setOpen(jsonWrapper.getBigDecimal("o"));
            result.setHigh(jsonWrapper.getBigDecimal("h"));
            result.setLow(jsonWrapper.getBigDecimal("l"));
            result.setTotalTradedBaseAssetVolume(jsonWrapper.getBigDecimal("v"));
            result.setTotalTradedQuoteAssetVolume(jsonWrapper.getBigDecimal("q"));
            result.setOpenTime(jsonWrapper.getInteger("O"));
            result.setCloseTime(jsonWrapper.getInteger("C"));
            result.setFirstId(jsonWrapper.getInteger("F"));
            result.setLastId(jsonWrapper.getInteger("L"));
            result.setCount(jsonWrapper.getInteger("n"));
            return result;
        };
        return request;
    }

    WebsocketRequest<List<SymbolTickerEvent>> subscribeAllTickerEvent(
            SubscriptionListener<List<SymbolTickerEvent>> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<List<SymbolTickerEvent>> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***All Market Tickers"; 
        request.connectionHandler = (connection) -> connection.send(Channels.tickerChannel());

        request.jsonParser = (jsonWrapper) -> {
            List<SymbolTickerEvent> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach(item -> {
                SymbolTickerEvent element = new SymbolTickerEvent();
                element.setEventType(item.getString("e"));
                element.setEventTime(item.getInteger("E"));
                element.setSymbol(item.getString("s"));
                element.setPriceChange(item.getBigDecimal("p"));
                element.setPriceChangePercent(item.getBigDecimal("P"));
                element.setWeightedAvgPrice(item.getBigDecimal("w"));
                element.setFirstPrice(item.getBigDecimal("x"));
                element.setLastPrice(item.getBigDecimal("c"));
                element.setLastQty(item.getBigDecimal("Q"));
                element.setBestBidPrice(item.getBigDecimal("b"));
                element.setBestBidQty(item.getBigDecimal("B"));
                element.setBestAskPrice(item.getBigDecimal("a"));
                element.setBestAskQty(item.getBigDecimal("A"));
                element.setOpen(item.getBigDecimal("o"));
                element.setHigh(item.getBigDecimal("h"));
                element.setLow(item.getBigDecimal("l"));
                element.setTotalTradedBaseAssetVolume(item.getBigDecimal("v"));
                element.setTotalTradedQuoteAssetVolume(item.getBigDecimal("q"));
                element.setOpenTime(item.getInteger("O"));
                element.setCloseTime(item.getInteger("C"));
                element.setFirstId(item.getInteger("F"));
                element.setLastId(item.getInteger("L"));
                element.setCount(item.getInteger("n"));
                result.add(element);
            });
           
            return result;
        };
        return request;
    }

}
