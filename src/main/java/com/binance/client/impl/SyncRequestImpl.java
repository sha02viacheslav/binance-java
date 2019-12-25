package com.binance.client.impl;

import com.binance.client.SyncRequestClient;
import com.binance.client.exception.BinanceApiException;
import com.binance.client.model.CoinInformation;
import com.binance.client.model.SystemStatus;
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

    @Override
    public SystemStatus getSystemStatus() {
        return RestApiInvoker.callSync(requestImpl.getSystemStatus());
    }

    @Override
    public List<CoinInformation> getAllCoinsInformation() {
        return RestApiInvoker.callSync(requestImpl.getAllCoinsInformation());
    }

    @Override
    public Long postWithdrawSapi(String coin, String address, String amount, String network, String addressTag,
            String name) {
        return RestApiInvoker.callSync(requestImpl.postWithdrawSapi(coin, address, amount, network, addressTag, name));
    }

    @Override
    public Long postWithdraw(String asset, String address, String amount, String network, String addressTag, String name) {
        return RestApiInvoker.callSync(requestImpl.postWithdraw(asset, address, amount, network, addressTag, name));
    }
}
