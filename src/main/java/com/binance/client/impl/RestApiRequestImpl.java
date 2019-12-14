package com.binance.client.impl;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

import com.binance.client.RequestOptions;
import com.binance.client.exception.BinanceApiException;
import com.binance.client.impl.utils.JsonWrapper;
import com.binance.client.impl.utils.UrlParamsBuilder;
import com.binance.client.model.TradeStatistics;


class RestApiRequestImpl {

    private String apiKey;
    private String secretKey;
    private String serverUrl;
    private RequestOptions options;
    private String tradingHost;

    RestApiRequestImpl(String apiKey, String secretKey, RequestOptions options) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.serverUrl = options.getUrl();
        this.options = options;
        try {
            String host = new URL(this.options.getUrl()).getHost();
            this.tradingHost = host;
        } catch (Exception e) {
        }
    }

    private Request createRequestByGet(String address, UrlParamsBuilder builder) {
        System.out.println(serverUrl);
        return createRequestByGet(serverUrl, address, builder);
    }

    private Request createRequestByGet(String url, String address, UrlParamsBuilder builder) {
        return createRequest(url, address, builder);
    }

    private Request createRequest(String url, String address, UrlParamsBuilder builder) {
        String requestUrl = url + address;
        System.out.print(requestUrl);
        if (builder != null) {
            if (builder.hasPostParam()) {
                return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                        .addHeader("Content-Type", "application/json").build();
            } else {
                return new Request.Builder().url(requestUrl + builder.buildUrl())
                        .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
            }
        } else {
            return new Request.Builder().url(requestUrl).addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
        }
    }

    private Request createRequestWithSignature(String url, String address, String host, UrlParamsBuilder builder) {
        if (builder == null) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Invoking] Builder is null when create request with Signature");
        }
        String requestUrl = url + address;
        if (builder.hasPostParam()) {
            new ApiSignature().createSignature(apiKey, secretKey, "POST", host, address, builder);
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                    .addHeader("Content-Type", "application/json").build();
        } else {
            new ApiSignature().createSignature(apiKey, secretKey, "GET", host, address, builder);
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl).addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
        }
    }

    private Request createRequestByPostWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, tradingHost, builder.setPostMode(true));
    }

    private Request createRequestByGetWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, tradingHost, builder);
    }

    RestApiRequest<TradeStatistics> get24HTradeStatistics(String symbol) {
        InputChecker.checker().checkSymbol(symbol);
        RestApiRequest<TradeStatistics> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build().putToUrl("symbol", symbol);
        request.request = createRequestByGet("/api/v3/ticker/24hr", builder);
        request.jsonParser = (jsonWrapper -> {
            TradeStatistics tradeStatistics = new TradeStatistics();
            tradeStatistics.setTimestamp(jsonWrapper.getLong("ts"));
            JsonWrapper tick = jsonWrapper.getJsonObject("tick");
            tradeStatistics.setAmount(tick.getBigDecimal("amount"));
            tradeStatistics.setOpen(tick.getBigDecimal("open"));
            tradeStatistics.setClose(tick.getBigDecimal("close"));
            tradeStatistics.setHigh(tick.getBigDecimal("high"));
            tradeStatistics.setLow(tick.getBigDecimal("low"));
            // tradeStatistics.setId(tick.getLong("id"));
            tradeStatistics.setCount(tick.getLong("count"));
            tradeStatistics.setVolume(tick.getBigDecimal("vol"));
            return tradeStatistics;
        });
        return request;
    }



}
