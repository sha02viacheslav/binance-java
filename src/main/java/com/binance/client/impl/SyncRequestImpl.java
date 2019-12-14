package com.binance.client.impl;

import com.binance.client.SyncRequestClient;
import com.binance.client.exception.BinanceApiException;
import com.binance.client.model.TradeStatistics;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SyncRequestImpl implements SyncRequestClient {

    private final RestApiRequestImpl requestImpl;

    SyncRequestImpl(RestApiRequestImpl requestImpl) {
        this.requestImpl = requestImpl;
    }

    @Override
    public TradeStatistics get24HTradeStatistics(String symbol) {
        return RestApiInvoker.callSync(requestImpl.get24HTradeStatistics(symbol));
    }
}
