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
import com.binance.client.impl.utils.JsonWrapperArray;
import com.binance.client.impl.utils.UrlParamsBuilder;
import com.binance.client.model.CoinInformation;
import com.binance.client.model.DepositHistorySapi;
import com.binance.client.model.Network;
import com.binance.client.model.SystemStatus;
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
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else {
            new ApiSignature().createSignature(apiKey, secretKey, "GET", host, address, builder);
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
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
            TradeStatistics result = new TradeStatistics();
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setPriceChange(jsonWrapper.getBigDecimal("priceChange"));
            result.setPriceChangePercent(jsonWrapper.getBigDecimal("priceChangePercent"));
            result.setWeightedAvgPrice(jsonWrapper.getBigDecimal("weightedAvgPrice"));
            result.setPrevClosePrice(jsonWrapper.getBigDecimal("prevClosePrice"));
            result.setLastPrice(jsonWrapper.getBigDecimal("lastPrice"));
            result.setLastQty(jsonWrapper.getBigDecimal("lastQty"));
            result.setBidPrice(jsonWrapper.getBigDecimal("bidPrice"));
            result.setAskPrice(jsonWrapper.getBigDecimal("askPrice"));
            result.setOpenPrice(jsonWrapper.getBigDecimal("openPrice"));
            result.setHighPrice(jsonWrapper.getBigDecimal("highPrice"));
            result.setLowPrice(jsonWrapper.getBigDecimal("lowPrice"));
            result.setVolume(jsonWrapper.getBigDecimal("volume"));
            result.setQuoteVolume(jsonWrapper.getBigDecimal("quoteVolume"));
            result.setOpenTime(jsonWrapper.getInteger("openTime"));
            result.setCloseTime(jsonWrapper.getInteger("closeTime"));
            result.setFirstId(jsonWrapper.getInteger("firstId"));
            result.setLastId(jsonWrapper.getInteger("lastId"));
            result.setCount(jsonWrapper.getInteger("count"));
            return result;
        });
        return request;
    }

    RestApiRequest<SystemStatus> getSystemStatus() {
        RestApiRequest<SystemStatus> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGet("/wapi/v3/systemStatus.html", builder);

        request.jsonParser = (jsonWrapper -> {
            SystemStatus result = new SystemStatus();
            result.setStatus(jsonWrapper.getString("status"));
            result.setMsg(jsonWrapper.getString("msg"));
            return result;
        });
        return request;
    }

    RestApiRequest<List<CoinInformation>> getAllCoinsInformation() {
        RestApiRequest<List<CoinInformation>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/sapi/v1/capital/config/getall", builder);

        request.jsonParser = (jsonWrapper -> {
            List<CoinInformation> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                CoinInformation element = new CoinInformation();
                element.setCoin(item.getString("coin"));
                element.setDepositAllEnable(item.getBoolean("depositAllEnable"));
                element.setFree(item.getBigDecimal("free"));
                element.setFreeze(item.getBigDecimal("freeze"));
                element.setIpoable(item.getBigDecimal("ipoable"));
                element.setIpoing(item.getBigDecimal("ipoing"));
                element.setIsLegalMoney(item.getBoolean("isLegalMoney"));
                element.setLocked(item.getBigDecimal("locked"));
                element.setName(item.getString("name"));
                element.setStorage(item.getBigDecimal("storage"));
                element.setTrading(item.getBoolean("trading"));
                element.setWithdrawAllEnable(item.getBoolean("withdrawAllEnable"));
                element.setWithdrawing(item.getBigDecimal("withdrawing"));
                List<Network> networkList = new LinkedList<>();
                JsonWrapperArray list = item.getJsonArray("networkList");
                list.forEach((val) -> {
                    Network network = new Network();
                    network.setAddressRegex(val.getString("addressRegex"));
                    network.setCoin(val.getString("coin"));
                    network.setDepositDesc(val.getString("depositDesc"));
                    network.setDepositEnable(val.getBoolean("depositEnable"));
                    network.setIsDefault(val.getBoolean("isDefault"));
                    network.setMemoRegex(val.getString("memoRegex"));
                    network.setName(val.getString("name"));
                    network.setNetwork(val.getString("network"));
                    network.setResetAddressStatus(val.getBoolean("resetAddressStatus"));
                    network.setSpecialTips(val.getStringOrDefault("specialTips", ""));
                    network.setWithdrawDesc(val.getString("withdrawDesc"));
                    network.setWithdrawEnable(val.getBoolean("withdrawEnable"));
                    network.setWithdrawFee(val.getBigDecimalOrDefault("withdrawFee", null));
                    network.setWithdrawIntegerMultiple(val.getBigDecimal("withdrawIntegerMultiple"));
                    network.setWithdrawMin(val.getBigDecimalOrDefault("withdrawMin", null));
                    networkList.add(network);
                });
                element.setNetworkList(networkList);
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<Long> postWithdrawSapi(String coin, String address, String amount, String network, String addressTag,
            String name) {
        RestApiRequest<Long> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("coin", coin)
                .putToUrl("address", address)
                .putToUrl("amount", amount)
                .putToUrl("network", network)
                .putToUrl("addressTag", addressTag)
                .putToUrl("name", name);
        request.request = createRequestByPostWithSignature("/sapi/v1/capital/withdraw/apply", builder);

        request.jsonParser = (jsonWrapper -> {
            Long result = jsonWrapper.getLong("id");
            return result;
        });
        return request;
    }

    RestApiRequest<Long> postWithdraw(String asset, String address, String amount, String network, 
            String addressTag, String name) {
        RestApiRequest<Long> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset)
                .putToUrl("address", address)
                .putToUrl("amount", amount)
                .putToUrl("network", network)
                .putToUrl("addressTag", addressTag)
                .putToUrl("name", name);
        request.request = createRequestByPostWithSignature("/wapi/v3/withdraw.html", builder);

        request.jsonParser = (jsonWrapper -> {
            Long result = jsonWrapper.getLong("id");
            return result;
        });
        return request;
    }

    RestApiRequest<List<DepositHistorySapi>> getDepositHistorySapi(String coin, Integer status, Long startTime, Long endTime, Integer offset) {
        RestApiRequest<List<DepositHistorySapi>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("coin", coin)
                .putToUrl("status", status)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("offset", offset);
        request.request = createRequestByGetWithSignature("/sapi/v1/capital/deposit/hisrec", builder);

        request.jsonParser = (jsonWrapper -> {
            List<DepositHistorySapi> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                DepositHistorySapi element = new DepositHistorySapi();
                element.setAddress(item.getString("address"));
                element.setAddressTag(item.getString("addressTag"));
                element.setAmount(item.getBigDecimal("amount"));
                element.setCoin(item.getString("coin"));
                element.setInsertTime(item.getInteger("insertTime"));
                element.setNetwork(item.getString("network"));
                element.setStatus(item.getInteger("status"));
                element.setTxId(item.getString("txId"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

}
