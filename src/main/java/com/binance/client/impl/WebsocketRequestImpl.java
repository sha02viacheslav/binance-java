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
import com.binance.client.model.event.DiffDepthEvent;
import com.binance.client.model.event.SymbolBookTickerEvent;
import com.binance.client.model.event.SymbolMiniTickerEvent;
import com.binance.client.model.event.SymbolTickerEvent;
import com.binance.client.model.event.TradeEvent;
import com.binance.client.model.market.OrderBook;
import com.binance.client.model.market.OrderBookEntry;
import com.binance.client.model.user.BalanceUpdate;
import com.binance.client.model.user.ExecutionReport;
import com.binance.client.model.user.ListStatus;
import com.binance.client.model.user.OutboundAccountInfo;
import com.binance.client.model.user.OutboundAccountPosition;
import com.binance.client.model.user.UserDataUpdateEvent;
import com.binance.client.model.wallet.Balance;

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
            result.setEventTime(jsonWrapper.getLong("E"));
            result.setSymbol(jsonWrapper.getString("s"));
            result.setId(jsonWrapper.getLong("a"));
            result.setPrice(jsonWrapper.getBigDecimal("p"));
            result.setQty(jsonWrapper.getBigDecimal("q"));
            result.setFirstId(jsonWrapper.getLong("f"));
            result.setLastId(jsonWrapper.getLong("l"));
            result.setTime(jsonWrapper.getLong("T"));
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
            result.setEventTime(jsonWrapper.getLong("E"));
            result.setSymbol(jsonWrapper.getString("s"));
            result.setId(jsonWrapper.getLong("t"));
            result.setPrice(jsonWrapper.getBigDecimal("p"));
            result.setQty(jsonWrapper.getBigDecimal("q"));
            result.setBuyerOrderId(jsonWrapper.getLong("b"));
            result.setSellerOrderId(jsonWrapper.getLong("a"));
            result.setTime(jsonWrapper.getLong("T"));
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
            result.setEventTime(jsonWrapper.getLong("E"));
            result.setSymbol(jsonWrapper.getString("s"));
            JsonWrapper jsondata = jsonWrapper.getJsonObject("k");
            result.setStartTime(jsondata.getLong("t"));
            result.setCloseTime(jsondata.getLong("T"));
            result.setSymbol(jsondata.getString("s"));
            result.setInterval(jsondata.getString("i"));
            result.setFirstTradeId(jsondata.getLong("f"));
            result.setLastTradeId(jsondata.getLong("L"));
            result.setOpen(jsondata.getBigDecimal("o"));
            result.setClose(jsondata.getBigDecimal("c"));
            result.setHigh(jsondata.getBigDecimal("h"));
            result.setLow(jsondata.getBigDecimal("l"));
            result.setVolume(jsondata.getBigDecimal("v"));
            result.setNumTrades(jsondata.getLong("n"));
            result.setIsClosed(jsondata.getBoolean("x"));
            result.setQuoteAssetVolume(jsondata.getBigDecimal("q"));
            result.setTakerBuyBaseAssetVolume(jsondata.getBigDecimal("V"));
            result.setTakerBuyQuoteAssetVolume(jsondata.getBigDecimal("Q"));
            result.setIgnore(jsondata.getLong("B"));
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
            result.setEventTime(jsonWrapper.getLong("E"));
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
            result.setEventTime(jsonWrapper.getLong("E"));
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
            result.setEventTime(jsonWrapper.getLong("E"));
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
            result.setOpenTime(jsonWrapper.getLong("O"));
            result.setCloseTime(jsonWrapper.getLong("C"));
            result.setFirstId(jsonWrapper.getLong("F"));
            result.setLastId(jsonWrapper.getLong("L"));
            result.setCount(jsonWrapper.getLong("n"));
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
                element.setEventTime(item.getLong("E"));
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
                element.setOpenTime(item.getLong("O"));
                element.setCloseTime(item.getLong("C"));
                element.setFirstId(item.getLong("F"));
                element.setLastId(item.getLong("L"));
                element.setCount(item.getLong("n"));
                result.add(element);
            });
           
            return result;
        };
        return request;
    }

    WebsocketRequest<SymbolBookTickerEvent> subscribeSymbolBookTickerEvent(String symbol,
            SubscriptionListener<SymbolBookTickerEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(symbol, "symbol")
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<SymbolBookTickerEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***Individual Symbol Book Ticker for " + symbol + "***"; 
        request.connectionHandler = (connection) -> connection.send(Channels.bookTickerChannel(symbol));

        request.jsonParser = (jsonWrapper) -> {
            SymbolBookTickerEvent result = new SymbolBookTickerEvent();
            result.setOrderBookUpdateId(jsonWrapper.getLong("u"));
            result.setSymbol(jsonWrapper.getString("s"));
            result.setBestBidPrice(jsonWrapper.getBigDecimal("b"));
            result.setBestBidQty(jsonWrapper.getBigDecimal("B"));
            result.setBestAskPrice(jsonWrapper.getBigDecimal("a"));
            result.setBestAskQty(jsonWrapper.getBigDecimal("A"));
            return result;
        };
        return request;
    }

    WebsocketRequest<SymbolBookTickerEvent> subscribeAllBookTickerEvent(
            SubscriptionListener<SymbolBookTickerEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<SymbolBookTickerEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***All Market Book Tickers"; 
        request.connectionHandler = (connection) -> connection.send(Channels.bookTickerChannel());

        request.jsonParser = (jsonWrapper) -> {
            SymbolBookTickerEvent result = new SymbolBookTickerEvent();
            result.setOrderBookUpdateId(jsonWrapper.getLong("u"));
            result.setSymbol(jsonWrapper.getString("s"));
            result.setBestBidPrice(jsonWrapper.getBigDecimal("b"));
            result.setBestBidQty(jsonWrapper.getBigDecimal("B"));
            result.setBestAskPrice(jsonWrapper.getBigDecimal("a"));
            result.setBestAskQty(jsonWrapper.getBigDecimal("A"));
            return result;
        };
        return request;
    }

    WebsocketRequest<OrderBook> subscribeBookDepthEvent(String symbol, Integer limit,
            SubscriptionListener<OrderBook> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(symbol, "symbol")
                .shouldNotNull(limit, "limit")
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<OrderBook> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***Partial Book Depth for " + symbol + "***"; 
        request.connectionHandler = (connection) -> connection.send(Channels.bookDepthChannel(symbol, limit));

        request.jsonParser = (jsonWrapper) -> {
            OrderBook result = new OrderBook();
            result.setLastUpdateId(jsonWrapper.getLong("lastUpdateId"));

            List<OrderBookEntry> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("bids");
            dataArray.forEachAsArray((item) -> {
                OrderBookEntry element = new OrderBookEntry();
                element.setPrice(item.getBigDecimalAt(0));
                element.setQty(item.getBigDecimalAt(1));
                elementList.add(element);
            });
            result.setBids(elementList);

            List<OrderBookEntry> askList = new LinkedList<>();
            JsonWrapperArray askArray = jsonWrapper.getJsonArray("asks");
            askArray.forEachAsArray((item) -> {
                OrderBookEntry element = new OrderBookEntry();
                element.setPrice(item.getBigDecimalAt(0));
                element.setQty(item.getBigDecimalAt(1));
                askList.add(element);
            });
            result.setAsks(askList);
            
            return result;
        };
        return request;
    }

    WebsocketRequest<DiffDepthEvent> subscribeDiffDepthEvent(String symbol,
            SubscriptionListener<DiffDepthEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(symbol, "symbol")
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<DiffDepthEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***Partial Book Depth for " + symbol + "***"; 
        request.connectionHandler = (connection) -> connection.send(Channels.diffDepthChannel(symbol));

        request.jsonParser = (jsonWrapper) -> {
            DiffDepthEvent result = new DiffDepthEvent();
            result.setEventType(jsonWrapper.getString("e"));
            result.setEventTime(jsonWrapper.getLong("E"));
            result.setSymbol(jsonWrapper.getString("s"));
            result.setFirstUpdateId(jsonWrapper.getLong("U"));
            result.setFinalUpdateId(jsonWrapper.getLong("u"));

            List<OrderBookEntry> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("b");
            dataArray.forEachAsArray((item) -> {
                OrderBookEntry element = new OrderBookEntry();
                element.setPrice(item.getBigDecimalAt(0));
                element.setQty(item.getBigDecimalAt(1));
                elementList.add(element);
            });
            result.setBids(elementList);

            List<OrderBookEntry> askList = new LinkedList<>();
            JsonWrapperArray askArray = jsonWrapper.getJsonArray("a");
            askArray.forEachAsArray((item) -> {
                OrderBookEntry element = new OrderBookEntry();
                element.setPrice(item.getBigDecimalAt(0));
                element.setQty(item.getBigDecimalAt(1));
                askList.add(element);
            });
            result.setAsks(askList);
            
            return result;
        };
        return request;
    }

    WebsocketRequest<UserDataUpdateEvent> subscribeUserDataEvent(String listenKey,
            SubscriptionListener<UserDataUpdateEvent> subscriptionListener,
            SubscriptionErrorHandler errorHandler) {
        InputChecker.checker()
                .shouldNotNull(listenKey, "listenKey")
                .shouldNotNull(subscriptionListener, "listener");
        WebsocketRequest<UserDataUpdateEvent> request = new WebsocketRequest<>(subscriptionListener, errorHandler);
        request.name = "***User Data***"; 
        request.connectionHandler = (connection) -> connection.send(Channels.userDataChannel(listenKey));

        request.jsonParser = (jsonWrapper) -> {
            UserDataUpdateEvent result = new UserDataUpdateEvent();
            result.setEventType(jsonWrapper.getString("e"));
            result.setEventTime(jsonWrapper.getLong("E"));

            if(jsonWrapper.getString("e").equals("outboundAccountInfo")) {
                OutboundAccountInfo outboundAccountInfo = new OutboundAccountInfo();
                outboundAccountInfo.setMakerCommission(jsonWrapper.getLong("m"));
                outboundAccountInfo.setTakerCommission(jsonWrapper.getLong("t"));
                outboundAccountInfo.setBuyerCommission(jsonWrapper.getLong("b"));
                outboundAccountInfo.setSellerCommission(jsonWrapper.getLong("s"));
                outboundAccountInfo.setCanTrade(jsonWrapper.getBoolean("T"));
                outboundAccountInfo.setCanWithdraw(jsonWrapper.getBoolean("W"));
                outboundAccountInfo.setCanDeposit(jsonWrapper.getBoolean("D"));
                outboundAccountInfo.setUpdateTime(jsonWrapper.getLong("u"));
                List<Balance> elementList = new LinkedList<>();
                JsonWrapperArray dataArray = jsonWrapper.getJsonArray("B");
                dataArray.forEach(item -> {
                    Balance balance = new Balance();
                    balance.setAsset(item.getString("a"));
                    balance.setFree(item.getBigDecimal("f"));
                    balance.setLocked(item.getBigDecimal("l"));
                    elementList.add(balance);
                });
                outboundAccountInfo.setBalances(elementList);
                result.setOutboundAccountInfo(outboundAccountInfo); 
            } else if(jsonWrapper.getString("e").equals("outboundAccountPosition")) {
                OutboundAccountPosition outboundAccountPosition = new OutboundAccountPosition();
                outboundAccountPosition.setUpdateTime(jsonWrapper.getLong("u"));
                List<Balance> elementList = new LinkedList<>();
                JsonWrapperArray dataArray = jsonWrapper.getJsonArray("B");
                dataArray.forEach(item -> {
                    Balance balance = new Balance();
                    balance.setAsset(item.getString("a"));
                    balance.setFree(item.getBigDecimal("f"));
                    balance.setLocked(item.getBigDecimal("l"));
                    elementList.add(balance);
                });
                outboundAccountPosition.setBalances(elementList);
                result.setOutboundAccountPosition(outboundAccountPosition); 
            } else if(jsonWrapper.getString("e").equals("balanceUpdate")) {
                BalanceUpdate balanceUpdate = new BalanceUpdate();
                balanceUpdate.setAsset(jsonWrapper.getString("a"));
                balanceUpdate.setDelta(jsonWrapper.getBigDecimal("d"));
                balanceUpdate.setClearTime(jsonWrapper.getLong("T"));
                result.setBalanceUpdate(balanceUpdate); 
            } else if(jsonWrapper.getString("e").equals("executionReport")) {
                ExecutionReport executionReport = new ExecutionReport();
                executionReport.setSymbol(jsonWrapper.getString("s"));
                executionReport.setClientOrderId(jsonWrapper.getString("c"));
                executionReport.setSide(jsonWrapper.getString("S"));
                executionReport.setType(jsonWrapper.getString("o"));
                executionReport.setTimeInForce(jsonWrapper.getString("f"));
                executionReport.setOrderQty(jsonWrapper.getBigDecimal("q"));
                executionReport.setOrderPrice(jsonWrapper.getBigDecimal("p"));
                executionReport.setStopPrice(jsonWrapper.getBigDecimal("P"));
                executionReport.setIcebergQty(jsonWrapper.getBigDecimal("F"));
                executionReport.setOrderListId(jsonWrapper.getLong("g"));
                executionReport.setOrigClientOrderId(jsonWrapper.getString("C"));
                executionReport.setExecutionType(jsonWrapper.getString("x"));
                executionReport.setOrderStatus(jsonWrapper.getString("X"));
                executionReport.setErrorCode(jsonWrapper.getString("r"));
                executionReport.setOrderId(jsonWrapper.getLong("i"));
                executionReport.setLastExecutedQty(jsonWrapper.getBigDecimal("l"));
                executionReport.setCumulativeFilledQty(jsonWrapper.getBigDecimal("z"));
                executionReport.setLastExecutedPrice(jsonWrapper.getBigDecimal("L"));
                executionReport.setCommissionAmount(jsonWrapper.getLong("n"));
                executionReport.setCommissionAsset(jsonWrapper.getString("N"));
                executionReport.setTransactionTime(jsonWrapper.getLong("T"));
                executionReport.setTradeID(jsonWrapper.getLong("t"));
                executionReport.setIgnore(jsonWrapper.getLong("I"));
                executionReport.setIsOrderBook(jsonWrapper.getBoolean("w"));
                executionReport.setIsMarkerSide(jsonWrapper.getBoolean("m"));
                executionReport.setIsIgnore(jsonWrapper.getBoolean("M"));
                executionReport.setOrderCreationTime(jsonWrapper.getLong("O"));
                executionReport.setCumulativeQuoteAssetQty(jsonWrapper.getBigDecimal("Z"));
                executionReport.setLastQuoteAssetQty(jsonWrapper.getBigDecimal("Y"));
                executionReport.setQuoteOrderQty(jsonWrapper.getBigDecimal("Q"));
                result.setExecutionReport(executionReport); 
            } else if(jsonWrapper.getString("e").equals("listStatus")) {
                ListStatus listStatus = new ListStatus();
                listStatus.setSymbol(jsonWrapper.getString("s"));
                listStatus.setOrderListId(jsonWrapper.getLong("g"));
                listStatus.setContingencyType(jsonWrapper.getString("c"));
                listStatus.setListStatusType(jsonWrapper.getString("l"));
                listStatus.setListOrderStatus(jsonWrapper.getString("L"));
                listStatus.setListRejectReason(jsonWrapper.getString("r"));
                listStatus.setListClientOrderId(jsonWrapper.getString("C"));
                listStatus.setTransactionTime(jsonWrapper.getLong("T"));
                result.setListStatus(listStatus); 
            }
            
            return result;
        };
        return request;
    }

}
