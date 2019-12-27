package com.binance.client.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.binance.client.RequestOptions;
import com.binance.client.exception.BinanceApiException;
import com.binance.client.impl.utils.JsonWrapper;
import com.binance.client.impl.utils.JsonWrapperArray;
import com.binance.client.impl.utils.UrlParamsBuilder;
import com.binance.client.model.wallet.AccountApiTradingStatus;
import com.binance.client.model.wallet.AccountStatus;
import com.binance.client.model.wallet.AssetDetail;
import com.binance.client.model.wallet.AssetDividendRecord;
import com.binance.client.model.wallet.Balance;
import com.binance.client.model.wallet.CoinInformation;
import com.binance.client.model.wallet.DepositAddress;
import com.binance.client.model.wallet.DepositAddressSapi;
import com.binance.client.model.wallet.DepositHistory;
import com.binance.client.model.wallet.DepositHistorySapi;
import com.binance.client.model.wallet.DustLog;
import com.binance.client.model.wallet.DustLogEntry;
import com.binance.client.model.wallet.DustTransfer;
import com.binance.client.model.wallet.DustTransferEntry;
import com.binance.client.model.wallet.FuturesSummary;
import com.binance.client.model.wallet.FuturesSummaryEntry;
import com.binance.client.model.wallet.Indicator;
import com.binance.client.model.wallet.IndicatorInfo;
import com.binance.client.model.wallet.MarginSummary;
import com.binance.client.model.wallet.MarginSummaryEntry;
import com.binance.client.model.wallet.MarginTradeCoeffVo;
import com.binance.client.model.wallet.MarginUserAssetVo;
import com.binance.client.model.wallet.Network;
import com.binance.client.model.wallet.SubAccount;
import com.binance.client.model.wallet.SubAccountDepositHistory;
import com.binance.client.model.wallet.SubAccountFuturesDetail;
import com.binance.client.model.wallet.SubAccountStatus;
import com.binance.client.model.wallet.SubAccountTransferHistory;
import com.binance.client.model.wallet.SubAccountMarginDetail;
import com.binance.client.model.wallet.SubAccountFuturesDetailAsset;
import com.binance.client.model.wallet.SubAccountFuturesPositionRisk;
import com.binance.client.model.wallet.SystemStatus;
import com.binance.client.model.wallet.TradeFee;
import com.binance.client.model.wallet.TradeStatistics;
import com.binance.client.model.wallet.TriggerCondition;
import com.binance.client.model.wallet.WithdrawHistory;
import com.binance.client.model.wallet.WithdrawHistorySapi;
import com.binance.client.model.market.AggregateTrade;
import com.binance.client.model.market.AveragePrice;
import com.binance.client.model.market.Candlestick;
import com.binance.client.model.market.ExchangeFilter;
import com.binance.client.model.market.ExchangeInfoEntry;
import com.binance.client.model.market.ExchangeInformation;
import com.binance.client.model.market.OrderBook;
import com.binance.client.model.market.OrderBookEntry;
import com.binance.client.model.market.PriceChangeTicker;
import com.binance.client.model.market.RateLimit;
import com.binance.client.model.market.SymbolOrderBook;
import com.binance.client.model.market.SymbolPrice;
import com.binance.client.model.market.Trade;
import com.binance.client.model.spot.AccountInformation;
import com.binance.client.model.spot.CancelOco;
import com.binance.client.model.spot.CancelOcoReport;
import com.binance.client.model.spot.CancelOrder;
import com.binance.client.model.spot.Fill;
import com.binance.client.model.spot.MyTrade;
import com.binance.client.model.spot.NewOco;
import com.binance.client.model.spot.NewOrder;
import com.binance.client.model.spot.Oco;
import com.binance.client.model.spot.OcoOrder;
import com.binance.client.model.spot.NewOcoReport;
import com.binance.client.model.spot.Order;
import com.binance.client.model.enums.*;
import com.binance.client.model.margin.MarginAsset;
import com.binance.client.model.margin.MarginNewOrder;
import com.binance.client.model.margin.MarginPair;
import com.binance.client.model.margin.MarginPriceIndex;

import okhttp3.Request;

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

    private Request createRequestWithSignature(String url, String address, UrlParamsBuilder builder) {
        if (builder == null) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Invoking] Builder is null when create request with Signature");
        }
        String requestUrl = url + address;
        new ApiSignature().createSignature(apiKey, secretKey, builder);
        if (builder.hasPostParam()) {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else if(builder.checkMethod("DELETE")) {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .delete()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        }
    }

    private Request createRequestByPostWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder.setMethod("POST"));
    }

    private Request createRequestByGetWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder);
    }

    private Request createRequestByDeleteWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder.setMethod("DELETE"));
    }

    private Request createRequestWithApikey(String url, String address, String host, UrlParamsBuilder builder) {
        if (builder == null) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Invoking] Builder is null when create request with Signature");
        }
        String requestUrl = url + address;
        if (builder.hasPostParam()) {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        }
    }

    private Request createRequestByGetWithApikey(String address, UrlParamsBuilder builder) {
        return createRequestWithApikey(serverUrl, address, tradingHost, builder);
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

    RestApiRequest<List<DepositHistory>> getDepositHistory(String asset, Integer status, Long startTime, Long endTime) {
        RestApiRequest<List<DepositHistory>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset)
                .putToUrl("status", status)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime);
        request.request = createRequestByGetWithSignature("/wapi/v3/depositHistory.html", builder);

        request.jsonParser = (jsonWrapper -> {
            List<DepositHistory> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("depositList");
            dataArray.forEach((item) -> {
                DepositHistory element = new DepositHistory();
                element.setInsertTime(item.getInteger("insertTime"));
                element.setAmount(item.getBigDecimal("amount"));
                element.setAsset(item.getString("asset"));
                element.setAddress(item.getString("address"));
                element.setTxId(item.getString("txId"));
                element.setStatus(item.getInteger("status"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<WithdrawHistorySapi>> getWithdrawHistorySapi(String coin, Integer status, Integer offset, 
            Integer limit, Long startTime, Long endTime) {
        RestApiRequest<List<WithdrawHistorySapi>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("coin", coin)
                .putToUrl("status", status)
                .putToUrl("offset", offset)
                .putToUrl("limit", limit)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime);
        request.request = createRequestByGetWithSignature("/sapi/v1/capital/withdraw/history", builder);

        request.jsonParser = (jsonWrapper -> {
            List<WithdrawHistorySapi> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                WithdrawHistorySapi element = new WithdrawHistorySapi();
                element.setAddress(item.getString("address"));
                element.setAmount(item.getBigDecimal("amount"));
                element.setApplyTime(item.getString("applyTime"));
                element.setCoin(item.getString("coin"));
                element.setId(item.getString("id"));
                element.setNetwork(item.getString("network"));
                element.setStatus(item.getInteger("status"));
                element.setTxId(item.getString("txId"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<WithdrawHistory>> getWithdrawHistory(String asset, Integer status, Long startTime, Long endTime) {
        RestApiRequest<List<WithdrawHistory>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset)
                .putToUrl("status", status)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime);
        request.request = createRequestByGetWithSignature("/wapi/v3/withdrawHistory.html", builder);

        request.jsonParser = (jsonWrapper -> {
            List<WithdrawHistory> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("withdrawList");
            dataArray.forEach((item) -> {
                WithdrawHistory element = new WithdrawHistory();
                element.setId(item.getString("id"));
                element.setAmount(item.getBigDecimal("amount"));
                element.setTransactionFee(item.getBigDecimal("transactionFee"));
                element.setAddress(item.getString("address"));
                element.setAsset(item.getString("asset"));
                element.setTxId(item.getString("txId"));
                element.setApplyTime(item.getInteger("applyTime"));
                element.setStatus(item.getInteger("status"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<DepositAddressSapi> getDepositAddressSapi(String coin, String network) {
        RestApiRequest<DepositAddressSapi> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("coin", coin)
                .putToUrl("network", network);
        request.request = createRequestByGetWithSignature("/sapi/v1/capital/deposit/address", builder);

        request.jsonParser = (jsonWrapper -> {
            DepositAddressSapi result = new DepositAddressSapi();
            result.setAddress(jsonWrapper.getString("address"));
            result.setCoin(jsonWrapper.getString("coin"));
            result.setTag(jsonWrapper.getString("tag"));
            result.setUrl(jsonWrapper.getString("url"));
            return result;
        });
        return request;
    }

    RestApiRequest<DepositAddress> getDepositAddress(String asset, String status) {
        RestApiRequest<DepositAddress> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset)
                .putToUrl("status", status);
        request.request = createRequestByGetWithSignature("/wapi/v3/depositAddress.html", builder);

        request.jsonParser = (jsonWrapper -> {
            DepositAddress result = new DepositAddress();
            result.setAddress(jsonWrapper.getString("address"));
            result.setAddressTag(jsonWrapper.getString("addressTag"));
            result.setAsset(jsonWrapper.getString("asset"));
            return result;
        });
        return request;
    }

    RestApiRequest<AccountStatus> getAccountStatus() {
        RestApiRequest<AccountStatus> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/wapi/v3/accountStatus.html", builder);

        request.jsonParser = (jsonWrapper -> {
            AccountStatus result = new AccountStatus();
            result.setMsg(jsonWrapper.getString("msg"));
            if(jsonWrapper.containKey("objs")) {
                List<String> data = new ArrayList<>();
                JsonWrapperArray jsonArray = jsonWrapper.getJsonArray("objs");
                for (int i = 0; jsonArray.getStringAt(i) != null; i++) {
                    data.add(jsonArray.getStringAt(i));  
                }
                result.setObjs(data);
            }
            return result;
        });
        return request;
    }

    RestApiRequest<AccountApiTradingStatus> getAccountApiTradingStatus() {
        RestApiRequest<AccountApiTradingStatus> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/wapi/v3/apiTradingStatus.html", builder);

        request.jsonParser = (jsonWrapper -> {
            AccountApiTradingStatus result = new AccountApiTradingStatus();
            JsonWrapper jsondata = jsonWrapper.getJsonObject("status");
            result.setIsLocked(jsondata.getBoolean("isLocked"));
            result.setPlannedRecoverTime(jsondata.getInteger("plannedRecoverTime"));
            result.setUpdateTime(jsondata.getInteger("updateTime"));
            TriggerCondition triggerCondition = new TriggerCondition();
            triggerCondition.setGCR(jsondata.getJsonObject("triggerCondition").getString("GCR"));
            triggerCondition.setIFER(jsondata.getJsonObject("triggerCondition").getString("IFER"));
            triggerCondition.setUFR(jsondata.getJsonObject("triggerCondition").getString("UFR"));
            result.setTriggerCondition(triggerCondition);
            List<Indicator> indicatorList =  new LinkedList<>();

            Set<String> keys = jsondata.getJsonObject("indicators").convert2JsonObject().keySet();
            keys.forEach((key) -> {
                JsonWrapperArray jsonIndicator = jsondata.getJsonObject("indicators").getJsonArray(key);
                Indicator indicator = new Indicator();
                indicator.setSymbol(key);
                List<IndicatorInfo> indicatorInfoList = new ArrayList<IndicatorInfo>();
                jsonIndicator.forEach((item) -> {
                    IndicatorInfo indicatorInfo = new IndicatorInfo();
                    indicatorInfo.setI(item.getString("i"));
                    indicatorInfo.setC(item.getInteger("c"));
                    indicatorInfo.setV(item.getBigDecimal("v"));
                    indicatorInfo.setT(item.getBigDecimal("t"));
                    indicatorInfoList.add(indicatorInfo);
                });
                indicator.setInformations(indicatorInfoList);

                indicatorList.add(indicator);
            });
            
            result.setIndicators(indicatorList);
            return result;
        });
        return request;
    }

    RestApiRequest<List<DustLog>> getDustLog() {
        RestApiRequest<List<DustLog>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/wapi/v3/userAssetDribbletLog.html", builder);

        request.jsonParser = (jsonWrapper -> {
            List<DustLog> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonObject("results").getJsonArray("rows");
            dataArray.forEach((item) -> {
                DustLog element = new DustLog();
                element.setTransferedTotal(item.getBigDecimal("transfered_total"));
                element.setServiceChargeTotal(item.getBigDecimal("service_charge_total"));
                element.setTranId(item.getInteger("tran_id"));
                element.setOperateTime(item.getString("operate_time"));
                List<DustLogEntry> dustLogEntries = new LinkedList<>();
                JsonWrapperArray list = item.getJsonArray("logs");
                list.forEach((val) -> {
                    DustLogEntry dustLogEntry = new DustLogEntry();
                    dustLogEntry.setTranId(val.getInteger("tranId"));
                    dustLogEntry.setServiceChargeAmount(val.getBigDecimal("serviceChargeAmount"));
                    dustLogEntry.setUid(val.getInteger("uid"));
                    dustLogEntry.setAmount(val.getBigDecimal("amount"));
                    dustLogEntry.setOperateTime(val.getString("operateTime"));
                    dustLogEntry.setTransferedAmount(val.getBigDecimal("transferedAmount"));
                    dustLogEntry.setFromAsset(val.getString("fromAsset"));
                    dustLogEntries.add(dustLogEntry);
                });
                element.setLogs(dustLogEntries);
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<TradeFee>> getTradeFee(String symbol) {
        RestApiRequest<List<TradeFee>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGetWithSignature("/wapi/v3/tradeFee.html", builder);

        request.jsonParser = (jsonWrapper -> {
            List<TradeFee> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("tradeFee");
            dataArray.forEach((item) -> {
                TradeFee element = new TradeFee();
                element.setSymbol(item.getString("symbol"));
                element.setMaker(item.getBigDecimal("maker"));
                element.setTaker(item.getBigDecimal("taker"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<AssetDetail>> getAssetDetail() {
        RestApiRequest<List<AssetDetail>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/wapi/v3/assetDetail.html", builder);

        request.jsonParser = (jsonWrapper -> {
            List<AssetDetail> result = new LinkedList<>();
            Set<String> keys = jsonWrapper.getJsonObject("assetDetail").convert2JsonObject().keySet();
            keys.forEach((key) -> {
                AssetDetail element = new AssetDetail();
                element.setAsset(key);
                JsonWrapper item = jsonWrapper.getJsonObject("assetDetail").getJsonObject(key);
                element.setMinWithdrawAmount(item.getBigDecimal("minWithdrawAmount"));
                element.setDepositStatus(item.getBoolean("depositStatus"));
                element.setWithdrawFee(item.getBigDecimal("withdrawFee"));
                element.setWithdrawStatus(item.getBoolean("withdrawStatus"));
                element.setDepositTip(item.getStringOrDefault("depositTip", null));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<SubAccount>> getSubAccounts(String email, String status, Integer page, Integer limit) {
        RestApiRequest<List<SubAccount>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email)
                .putToUrl("status", status)
                .putToUrl("page", page)
                .putToUrl("limit", limit);
        request.request = createRequestByGetWithSignature("/wapi/v3/sub-account/list.html", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SubAccount> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("subAccounts");
            dataArray.forEach((item) -> {
                SubAccount element = new SubAccount();
                element.setEmail(item.getString("email"));
                element.setStatus(item.getString("status"));
                element.setActivated(item.getBoolean("activated"));
                element.setMobile(item.getString("mobile"));
                element.setGAuth(item.getBoolean("gAuth"));
                element.setCreateTime(item.getInteger("createTime"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<SubAccountTransferHistory>> getSubAccountTransferHistory(String email, Long startTime, Long endTime,
            Integer page, Integer limit) {
        RestApiRequest<List<SubAccountTransferHistory>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("page", page)
                .putToUrl("limit", limit);
        request.request = createRequestByGetWithSignature("/wapi/v3/sub-account/transfer/history.html", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SubAccountTransferHistory> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("transfers");
            dataArray.forEach((item) -> {
                SubAccountTransferHistory element = new SubAccountTransferHistory();
                element.setFromEmail(item.getString("from"));
                element.setToEmail(item.getString("to"));
                element.setAsset(item.getBoolean("asset"));
                element.setQty(item.getString("qty"));
                element.setTime(item.getInteger("time"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<Long> postSubAccountTransfer(String fromEmail, String toEmail, String asset, String amount) {
        RestApiRequest<Long> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("fromEmail", fromEmail)
                .putToUrl("toEmail", toEmail)
                .putToUrl("asset", asset)
                .putToUrl("amount", amount);
        request.request = createRequestByPostWithSignature("/wapi/v3/sub-account/transfer.html", builder);

        request.jsonParser = (jsonWrapper -> {
            Long result = jsonWrapper.getLong("txnId");
            return result;
        });
        return request;
    }

    RestApiRequest<List<Balance>> getSubAccountAssets(String email, String symbol) {
        RestApiRequest<List<Balance>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email)
                .putToUrl("symbol", symbol);
        request.request = createRequestByGetWithSignature("/wapi/v3/sub-account/assets.html", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Balance> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("balances");
            dataArray.forEach((item) -> {
                Balance element = new Balance();
                element.setAsset(item.getString("asset"));
                element.setFree(item.getBigDecimal("free"));
                element.setLocked(item.getBigDecimal("locked"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<DepositAddressSapi> getSubAccountDepositAddress(String email, String coin, String network) {
        RestApiRequest<DepositAddressSapi> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email)
                .putToUrl("coin", coin)
                .putToUrl("network", network);
        request.request = createRequestByGetWithSignature("/sapi/v1/capital/deposit/subAddress", builder);

        request.jsonParser = (jsonWrapper -> {
            DepositAddressSapi result = new DepositAddressSapi();
            result.setAddress(jsonWrapper.getString("address"));
            result.setCoin(jsonWrapper.getString("coin"));
            result.setTag(jsonWrapper.getString("tag"));
            result.setUrl(jsonWrapper.getString("url"));
            return result;
        });
        return request;
    }

    RestApiRequest<List<SubAccountDepositHistory>> getSubAccountDepositHistory(String email, String coin, Integer status,
            Long startTime, Long endTime, Integer limit, Integer offset) {
        RestApiRequest<List<SubAccountDepositHistory>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email)
                .putToUrl("coin", coin)
                .putToUrl("status", status)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit)
                .putToUrl("offset", offset);
        request.request = createRequestByGetWithSignature("/sapi/v1/capital/deposit/subHisrec", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SubAccountDepositHistory> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                SubAccountDepositHistory element = new SubAccountDepositHistory();
                element.setAddress(item.getString("address"));
                element.setAddressTag(item.getString("addressTag"));
                element.setAmount(item.getBigDecimal("amount"));
                element.setCoin(item.getString("coin"));
                element.setInsertTime(item.getInteger("insertTime"));
                element.setStatus(item.getInteger("status"));
                element.setTxId(item.getString("txId"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<SubAccountStatus>> getSubAccountStatus(String email) {
        RestApiRequest<List<SubAccountStatus>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email);
        request.request = createRequestByGetWithSignature("/sapi/v1/sub-account/status", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SubAccountStatus> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                SubAccountStatus element = new SubAccountStatus();
                element.setEmail(item.getString("email"));
                element.setIsSubUserEnabled(item.getString("isSubUserEnabled"));
                element.setIsUserActive(item.getBoolean("isUserActive"));
                element.setInsertTime(item.getInteger("insertTime"));
                element.setIsMarginEnabled(item.getString("isMarginEnabled"));
                element.setIsFutureEnabled(item.getString("isFutureEnabled"));
                element.setMobile(item.getInteger("mobile"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<Boolean> postEnableMargin(String email) {
        RestApiRequest<Boolean> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email);
        request.request = createRequestByPostWithSignature("/sapi/v1/sub-account/margin/enable", builder);

        request.jsonParser = (jsonWrapper -> {
            Boolean result = jsonWrapper.getBoolean("isMarginEnabled");
            return result;
        });
        return request;
    }

    RestApiRequest<SubAccountMarginDetail> getSubAccountMarginDetail(String email) {
        RestApiRequest<SubAccountMarginDetail> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email);
        request.request = createRequestByGetWithSignature("/sapi/v1/sub-account/margin/account", builder);

        request.jsonParser = (jsonWrapper -> {
            SubAccountMarginDetail result = new SubAccountMarginDetail();
            result.setEmail(jsonWrapper.getString("email"));
            result.setMarginLevel(jsonWrapper.getBigDecimal("marginLevel"));
            result.setTotalAssetOfBtc(jsonWrapper.getBigDecimal("totalAssetOfBtc"));
            result.setTotalLiabilityOfBtc(jsonWrapper.getBigDecimal("totalLiabilityOfBtc"));
            result.setTotalNetAssetOfBtc(jsonWrapper.getBigDecimal("totalNetAssetOfBtc"));

            MarginTradeCoeffVo marginTradeCoeffVo = new MarginTradeCoeffVo();
            marginTradeCoeffVo.setForceLiquidationBar(jsonWrapper.getBigDecimal("forceLiquidationBar"));
            marginTradeCoeffVo.setMarginCallBar(jsonWrapper.getBigDecimal("marginCallBar"));
            marginTradeCoeffVo.setNormalBar(jsonWrapper.getBigDecimal("normalBar"));
            result.setMarginTradeCoeffVo(marginTradeCoeffVo);

            List<MarginUserAssetVo> marginUserAssetVoList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("marginUserAssetVoList");
            dataArray.forEach((item) -> {
                MarginUserAssetVo element = new MarginUserAssetVo();
                element.setAsset(item.getString("asset"));
                element.setBorrowed(item.getBigDecimal("borrowed"));
                element.setFree(item.getBigDecimal("free"));
                element.setInterest(item.getBigDecimal("interest"));
                element.setLocked(item.getBigDecimal("locked"));
                element.setNetAsset(item.getBigDecimal("netAsset"));
                marginUserAssetVoList.add(element);
            });
            result.setMarginUserAssetVoList(marginUserAssetVoList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<MarginSummary> getSubAccountMarginSummary() {
        RestApiRequest<MarginSummary> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/sapi/v1/sub-account/margin/accountSummary", builder);

        request.jsonParser = (jsonWrapper -> {
            MarginSummary result = new MarginSummary();
            result.setTotalAssetOfBtc(jsonWrapper.getBigDecimal("totalAssetOfBtc"));
            result.setTotalLiabilityOfBtc(jsonWrapper.getBigDecimal("totalLiabilityOfBtc"));
            result.setTotalNetAssetOfBtc(jsonWrapper.getBigDecimal("totalNetAssetOfBtc"));

            List<MarginSummaryEntry> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("subAccountList");
            dataArray.forEach((item) -> {
                MarginSummaryEntry element = new MarginSummaryEntry();
                element.setEmail(item.getString("email"));
                element.setTotalAssetOfBtc(item.getBigDecimal("totalAssetOfBtc"));
                element.setTotalLiabilityOfBtc(item.getBigDecimal("totalLiabilityOfBtc"));
                element.setTotalNetAssetOfBtc(item.getBigDecimal("totalNetAssetOfBtc"));
                elementList.add(element);
            });
            result.setSubAccountList(elementList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<Boolean> postEnableFutures(String email) {
        RestApiRequest<Boolean> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email);
        request.request = createRequestByPostWithSignature("/sapi/v1/sub-account/futures/enable", builder);

        request.jsonParser = (jsonWrapper -> {
            Boolean result = jsonWrapper.getBoolean("isFuturesEnabled");
            return result;
        });
        return request;
    }

    RestApiRequest<SubAccountFuturesDetail> getSubAccountFuturesDetail(String email) {
        RestApiRequest<SubAccountFuturesDetail> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email);
        request.request = createRequestByGetWithSignature("/sapi/v1/sub-account/futures/account", builder);

        request.jsonParser = (jsonWrapper -> {
            SubAccountFuturesDetail result = new SubAccountFuturesDetail();
            result.setEmail(jsonWrapper.getString("email"));
            result.setCanDeposit(jsonWrapper.getBoolean("canDeposit"));
            result.setCanTrade(jsonWrapper.getBoolean("canTrade"));
            result.setCanWithdraw(jsonWrapper.getBoolean("canWithdraw"));
            result.setFeeTier(jsonWrapper.getBigDecimal("feeTier"));
            result.setMaxWithdrawAmount(jsonWrapper.getBigDecimal("maxWithdrawAmount"));
            result.setTotalInitialMargin(jsonWrapper.getBigDecimal("totalInitialMargin"));
            result.setTotalMaintMargin(jsonWrapper.getBigDecimal("totalMaintMargin"));
            result.setTotalMarginBalance(jsonWrapper.getBigDecimal("totalMarginBalance"));
            result.setTotalOpenOrderInitialMargin(jsonWrapper.getBigDecimal("totalOpenOrderInitialMargin"));
            result.setTotalPositionInitialMargin(jsonWrapper.getBigDecimal("totalPositionInitialMargin"));
            result.setTotalUnrealizedProfit(jsonWrapper.getBigDecimal("totalUnrealizedProfit"));
            result.setTotalWalletBalance(jsonWrapper.getBigDecimal("totalWalletBalance"));
            result.setAsset(jsonWrapper.getString("asset"));
            result.setUpdateTime(jsonWrapper.getInteger("updateTime"));

            List<SubAccountFuturesDetailAsset> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("assets");
            dataArray.forEach((item) -> {
                SubAccountFuturesDetailAsset element = new SubAccountFuturesDetailAsset();
                element.setAsset(item.getString("asset"));
                element.setInitialMargin(item.getBigDecimal("initialMargin"));
                element.setMaintMargin(item.getBigDecimal("maintMargin"));
                element.setMarginBalance(item.getBigDecimal("marginBalance"));
                element.setMaxWithdrawAmount(item.getBigDecimal("maxWithdrawAmount"));
                element.setOpenOrderInitialMargin(item.getBigDecimal("openOrderInitialMargin"));
                element.setPositionInitialMargin(item.getBigDecimal("positionInitialMargin"));
                element.setUnrealizedProfit(item.getBigDecimal("unrealizedProfit"));
                element.setWalletBalance(item.getBigDecimal("walletBalance"));
                elementList.add(element);
            });
            result.setAssets(elementList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<FuturesSummary> getSubAccountFuturesSummary() {
        RestApiRequest<FuturesSummary> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/sapi/v1/sub-account/futures/accountSummary", builder);

        request.jsonParser = (jsonWrapper -> {
            FuturesSummary result = new FuturesSummary();
            result.setTotalInitialMargin(jsonWrapper.getBigDecimal("totalInitialMargin"));
            result.setTotalMaintMargin(jsonWrapper.getBigDecimal("totalMaintMargin"));
            result.setTotalMarginBalance(jsonWrapper.getBigDecimal("totalMarginBalance"));
            result.setTotalOpenOrderInitialMargin(jsonWrapper.getBigDecimal("totalOpenOrderInitialMargin"));
            result.setTotalPositionInitialMargin(jsonWrapper.getBigDecimal("totalPositionInitialMargin"));
            result.setTotalUnrealizedProfit(jsonWrapper.getBigDecimal("totalUnrealizedProfit"));
            result.setTotalWalletBalance(jsonWrapper.getBigDecimal("totalWalletBalance"));
            result.setAsset(jsonWrapper.getString("asset"));

            List<FuturesSummaryEntry> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("subAccountList");
            dataArray.forEach((item) -> {
                FuturesSummaryEntry element = new FuturesSummaryEntry();
                element.setEmail(item.getString("email"));
                element.setTotalInitialMargin(item.getBigDecimal("totalInitialMargin"));
                element.setTotalMaintMargin(item.getBigDecimal("totalMaintMargin"));
                element.setTotalMarginBalance(item.getBigDecimal("totalMarginBalance"));
                element.setTotalOpenOrderInitialMargin(item.getBigDecimal("totalOpenOrderInitialMargin"));
                element.setTotalPositionInitialMargin(item.getBigDecimal("totalPositionInitialMargin"));
                element.setTotalUnrealizedProfit(item.getBigDecimal("totalUnrealizedProfit"));
                element.setTotalWalletBalance(item.getBigDecimal("totalWalletBalance"));
                element.setAsset(item.getString("asset"));
                elementList.add(element);
            });
            result.setSubAccount(elementList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<List<SubAccountFuturesPositionRisk>> getSubAccountFuturesPositionRisk(String email) {
        RestApiRequest<List<SubAccountFuturesPositionRisk>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("email", email);
        request.request = createRequestByGetWithSignature("/sapi/v1/sub-account/futures/positionRisk", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SubAccountFuturesPositionRisk> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                SubAccountFuturesPositionRisk element = new SubAccountFuturesPositionRisk();
                element.setEntryPrice(item.getBigDecimal("entryPrice"));
                element.setLeverage(item.getBigDecimal("leverage"));
                element.setMaxNotional(item.getBigDecimal("maxNotional"));
                element.setLiquidationPrice(item.getBigDecimal("liquidationPrice"));
                element.setMarkPrice(item.getBigDecimal("markPrice"));
                element.setPositionAmt(item.getBigDecimal("positionAmt"));
                element.setSymbol(item.getString("symbol"));
                element.setUnRealizedProfit(item.getBigDecimal("unRealizedProfit"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<DustTransfer> postDustTransfer(String asset) {
        RestApiRequest<DustTransfer> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset);
        request.request = createRequestByPostWithSignature("/sapi/v1/asset/dust", builder);

        request.jsonParser = (jsonWrapper -> {
            DustTransfer result = new DustTransfer();
            result.setTotalServiceCharge(jsonWrapper.getBigDecimal("totalServiceCharge"));
            result.setTotalTransfered(jsonWrapper.getBigDecimal("totalTransfered"));

            List<DustTransferEntry> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("assets");
            dataArray.forEach((item) -> {
                DustTransferEntry element = new DustTransferEntry();
                element.setAmount(item.getBigDecimal("amount"));
                element.setFromAsset(item.getString("fromAsset"));
                element.setOperateTime(item.getInteger("operateTime"));
                element.setServiceChargeAmount(item.getBigDecimal("serviceChargeAmount"));
                element.setTranId(item.getInteger("tranId"));
                element.setTransferedAmount(item.getBigDecimal("transferedAmount"));
                elementList.add(element);
            });
            result.setTransferResult(elementList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<List<AssetDividendRecord>> getAssetDividendRecord(String asset, Long startTime, Long endTime) {
        RestApiRequest<List<AssetDividendRecord>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime);
        request.request = createRequestByGetWithSignature("/sapi/v1/asset/assetDividend", builder);

        request.jsonParser = (jsonWrapper -> {
            List<AssetDividendRecord> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("rows");
            dataArray.forEach((item) -> {
                AssetDividendRecord element = new AssetDividendRecord();
                element.setAmount(item.getBigDecimal("amount"));
                element.setAsset(item.getString("asset"));
                element.setDivTime(item.getInteger("divTime"));
                element.setEnInfo(item.getString("enInfo"));
                element.setTranId(item.getInteger("tranId"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<ExchangeInformation> getExchangeInformation() {
        RestApiRequest<ExchangeInformation> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGet("/api/v3/exchangeInfo", builder);

        request.jsonParser = (jsonWrapper -> {
            ExchangeInformation result = new ExchangeInformation();
            result.setTimezone(jsonWrapper.getString("timezone"));
            result.setServerTime(jsonWrapper.getInteger("serverTime"));

            List<RateLimit> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("rateLimits");
            dataArray.forEach((item) -> {
                RateLimit element = new RateLimit();
                element.setRateLimitType(item.getString("rateLimitType"));
                element.setInterval(item.getString("interval"));
                element.setIntervalNum(item.getInteger("intervalNum"));
                element.setLimit(item.getInteger("limit"));
                elementList.add(element);
            });
            result.setRateLimits(elementList);

            List<ExchangeFilter> filterList = new LinkedList<>();
            JsonWrapperArray filterArray = jsonWrapper.getJsonArray("exchangeFilters");
            filterArray.forEach((item) -> {
                ExchangeFilter filter = new ExchangeFilter();
                filter.setFilterType(item.getString("filterType"));
                filter.setMaxNumOrders(item.getInteger("maxNumOrders"));
                filter.setMaxNumAlgoOrders(item.getInteger("maxNumAlgoOrders"));
                filterList.add(filter);
            });
            result.setExchangeFilters(filterList);

            List<ExchangeInfoEntry> symbolList = new LinkedList<>();
            JsonWrapperArray symbolArray = jsonWrapper.getJsonArray("symbols");
            symbolArray.forEach((item) -> {
                ExchangeInfoEntry symbol = new ExchangeInfoEntry();
                symbol.setSymbol(item.getString("symbol"));
                symbol.setStatus(item.getString("status"));
                symbol.setBaseAsset(item.getString("baseAsset"));
                symbol.setBaseAssetPrecision(item.getInteger("baseAssetPrecision"));
                symbol.setQuoteAsset(item.getString("quoteAsset"));
                symbol.setQuotePrecision(item.getInteger("quotePrecision"));
                symbol.setOrderTypes(item.getJsonArray("orderTypes").convert2StringList());
                symbol.setIcebergAllowed(item.getBoolean("icebergAllowed"));
                symbol.setOcoAllowed(item.getBoolean("ocoAllowed"));
                symbol.setIsSpotTradingAllowed(item.getBoolean("isSpotTradingAllowed"));
                symbol.setIsMarginTradingAllowed(item.getBoolean("isMarginTradingAllowed"));
                List<List<Map<String, String>>> valList = new LinkedList<>();
                JsonWrapperArray valArray = item.getJsonArray("filters");
                valArray.forEach((val) -> {
                    valList.add(val.convert2DictList());
                });
                symbol.setFilters(valList);
                symbolList.add(symbol);
            });
            result.setSymbols(symbolList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<OrderBook> getOrderBook(String symbol, Integer limit) {
        RestApiRequest<OrderBook> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/api/v3/depth", builder);

        request.jsonParser = (jsonWrapper -> {
            OrderBook result = new OrderBook();
            result.setLastUpdateId(jsonWrapper.getInteger("lastUpdateId"));

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
        });
        return request;
    }

    RestApiRequest<List<Trade>> getRecentTrades(String symbol, Integer limit) {
        RestApiRequest<List<Trade>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/api/v3/trades", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Trade> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Trade element = new Trade();
                element.setId(item.getInteger("id"));
                element.setPrice(item.getBigDecimal("price"));
                element.setQty(item.getBigDecimal("qty"));
                element.setQuoteQty(item.getBigDecimal("quoteQty"));
                element.setTime(item.getInteger("time"));
                element.setIsBuyerMaker(item.getBoolean("isBuyerMaker"));
                element.setIsBestMatch(item.getBoolean("isBestMatch"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<List<Trade>> getOldTrades(String symbol, Integer limit, Long fromId) {
        RestApiRequest<List<Trade>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("limit", limit)
                .putToUrl("fromId", fromId);
        request.request = createRequestByGetWithApikey("/api/v3/historicalTrades", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Trade> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Trade element = new Trade();
                element.setId(item.getInteger("id"));
                element.setPrice(item.getBigDecimal("price"));
                element.setQty(item.getBigDecimal("qty"));
                element.setQuoteQty(item.getBigDecimal("quoteQty"));
                element.setTime(item.getInteger("time"));
                element.setIsBuyerMaker(item.getBoolean("isBuyerMaker"));
                element.setIsBestMatch(item.getBoolean("isBestMatch"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<List<AggregateTrade>> getAggregateTrades(String symbol, Long fromId, 
            Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<AggregateTrade>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("fromId", fromId)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/api/v3/aggTrades", builder);

        request.jsonParser = (jsonWrapper -> {
            List<AggregateTrade> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                AggregateTrade element = new AggregateTrade();
                element.setId(item.getInteger("a"));
                element.setPrice(item.getBigDecimal("p"));
                element.setQty(item.getBigDecimal("q"));
                element.setFirstId(item.getInteger("f"));
                element.setLastId(item.getInteger("l"));
                element.setTime(item.getInteger("T"));
                element.setIsBuyerMaker(item.getBoolean("m"));
                element.setIsBestMatch(item.getBoolean("M"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<List<Candlestick>> getCandlestick(String symbol, CandlestickInterval interval, Long startTime, 
            Long endTime, Integer limit) {
        RestApiRequest<List<Candlestick>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("interval", interval)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/api/v3/klines", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Candlestick> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEachAsArray((item) -> {
                Candlestick element = new Candlestick();
                element.setOpenTime(item.getLongAt(0));
                element.setOpen(item.getBigDecimalAt(1));
                element.setHigh(item.getBigDecimalAt(2));
                element.setLow(item.getBigDecimalAt(3));
                element.setClose(item.getBigDecimalAt(4));
                element.setVolume(item.getBigDecimalAt(5));
                element.setCloseTime(item.getLongAt(6));
                element.setQuoteAssetVolume(item.getBigDecimalAt(7));
                element.setNumTrades(item.getIntegerAt(8));
                element.setTakerBuyBaseAssetVolume(item.getBigDecimalAt(9));
                element.setTakerBuyQuoteAssetVolume(item.getBigDecimalAt(10));
                element.setIgnore(item.getBigDecimalAt(11));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<AveragePrice> getCurrentAveragePrice(String symbol) {
        RestApiRequest<AveragePrice> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGet("/api/v3/avgPrice", builder);

        request.jsonParser = (jsonWrapper -> {
            AveragePrice result = new AveragePrice();
            result.setMins(jsonWrapper.getInteger("mins"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            return result;
        });
        return request;
    }

    RestApiRequest<List<PriceChangeTicker>> get24hrTickerPriceChange(String symbol) {
        RestApiRequest<List<PriceChangeTicker>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGet("/api/v3/ticker/24hr", builder);

        request.jsonParser = (jsonWrapper -> {
            List<PriceChangeTicker> result = new LinkedList<>();
            JsonWrapperArray dataArray = new JsonWrapperArray(new JSONArray());
            if(jsonWrapper.containKey("data")) {
                dataArray = jsonWrapper.getJsonArray("data");
            } else {
                dataArray.add(jsonWrapper.convert2JsonObject());
            }
            dataArray.forEach((item) -> {
                PriceChangeTicker element = new PriceChangeTicker();
                element.setSymbol(item.getString("symbol"));
                element.setPriceChange(item.getBigDecimal("priceChange"));
                element.setPriceChangePercent(item.getBigDecimal("priceChangePercent"));
                element.setWeightedAvgPrice(item.getBigDecimal("weightedAvgPrice"));
                element.setPrevClosePrice(item.getBigDecimal("prevClosePrice"));
                element.setLastPrice(item.getBigDecimal("lastPrice"));
                element.setLastQty(item.getBigDecimal("lastQty"));
                element.setBidPrice(item.getBigDecimal("bidPrice"));
                element.setAskPrice(item.getBigDecimal("askPrice"));
                element.setOpenPrice(item.getBigDecimal("openPrice"));
                element.setHighPrice(item.getBigDecimal("highPrice"));
                element.setLowPrice(item.getBigDecimal("lowPrice"));
                element.setVolume(item.getBigDecimal("volume"));
                element.setQuoteVolume(item.getBigDecimal("quoteVolume"));
                element.setOpenTime(item.getInteger("openTime"));
                element.setCloseTime(item.getInteger("closeTime"));
                element.setFirstId(item.getInteger("firstId"));
                element.setLastId(item.getInteger("lastId"));
                element.setCount(item.getInteger("count"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<List<SymbolPrice>> getSymbolPriceTicker(String symbol) {
        RestApiRequest<List<SymbolPrice>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGet("/api/v3/ticker/price", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SymbolPrice> result = new LinkedList<>();
            JsonWrapperArray dataArray = new JsonWrapperArray(new JSONArray());
            if(jsonWrapper.containKey("data")) {
                dataArray = jsonWrapper.getJsonArray("data");
            } else {
                dataArray.add(jsonWrapper.convert2JsonObject());
            }
            dataArray.forEach((item) -> {
                SymbolPrice element = new SymbolPrice();
                element.setSymbol(item.getString("symbol"));
                element.setPrice(item.getBigDecimal("price"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<List<SymbolOrderBook>> getSymbolOrderBookTicker(String symbol) {
        RestApiRequest<List<SymbolOrderBook>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGet("/api/v3/ticker/bookTicker", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SymbolOrderBook> result = new LinkedList<>();
            JsonWrapperArray dataArray = new JsonWrapperArray(new JSONArray());
            if(jsonWrapper.containKey("data")) {
                dataArray = jsonWrapper.getJsonArray("data");
            } else {
                dataArray.add(jsonWrapper.convert2JsonObject());
            }
            dataArray.forEach((item) -> {
                SymbolOrderBook element = new SymbolOrderBook();
                element.setSymbol(item.getString("symbol"));
                element.setBidPrice(item.getBigDecimal("bidPrice"));
                element.setBidQty(item.getBigDecimal("bidQty"));
                element.setAskPrice(item.getBigDecimal("askPrice"));
                element.setAskQty(item.getBigDecimal("askQty"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<NewOrder> postOrder(String symbol, OrderSide side, OrderType orderType,
            TimeInForce timeInForce, String quantity, String price, String quoteOrderQty,
            String newClientOrderId, String stopPrice, String icebergQty, OrderRespType newOrderRespType) {
        RestApiRequest<NewOrder> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("side", side)
                .putToUrl("type", orderType)
                .putToUrl("timeInForce", timeInForce)
                .putToUrl("quantity", quantity)
                .putToUrl("quoteOrderQty", quoteOrderQty)
                .putToUrl("price", price)
                .putToUrl("newClientOrderId", newClientOrderId)
                .putToUrl("stopPrice", stopPrice)
                .putToUrl("icebergQty", icebergQty)
                .putToUrl("newOrderRespType", newOrderRespType);
        request.request = createRequestByPostWithSignature("/api/v3/order", builder);

        request.jsonParser = (jsonWrapper -> {
            NewOrder result = new NewOrder();
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setOrderId(jsonWrapper.getInteger("orderId"));
            result.setOrderListId(jsonWrapper.getInteger("orderListId"));
            result.setClientOrderId(jsonWrapper.getString("clientOrderId"));
            result.setTransactTime(jsonWrapper.getInteger("transactTime"));
            result.setPrice(jsonWrapper.getBigDecimalOrDefault("price", null));
            result.setOrigQty(jsonWrapper.getBigDecimalOrDefault("origQty", null));
            result.setExecutedQty(jsonWrapper.getBigDecimalOrDefault("executedQty", null));
            result.setCummulativeQuoteQty(jsonWrapper.getBigDecimalOrDefault("cummulativeQuoteQty", null));
            result.setStatus(jsonWrapper.getStringOrDefault("status", null));
            result.setTimeInForce(jsonWrapper.getStringOrDefault("timeInForce", null));
            result.setType(jsonWrapper.getStringOrDefault("type", null));
            result.setSide(jsonWrapper.getStringOrDefault("side", null));

            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("fills");
            List<Fill> elementList = new LinkedList<>();
            dataArray.forEach((item) -> {
                Fill element = new Fill();
                element.setPrice(item.getBigDecimal("price"));
                element.setQty(item.getBigDecimal("qty"));
                element.setCommission(item.getBigDecimal("commission"));
                element.setCommissionAsset(item.getString("commissionAsset"));
                elementList.add(element);
            });
            result.setFills(elementList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<CancelOrder> cancelOrder(String symbol, Long orderId, String origClientOrderId, String newClientOrderId) {
        RestApiRequest<CancelOrder> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("orderId", orderId)
                .putToUrl("origClientOrderId", origClientOrderId)
                .putToUrl("newClientOrderId", newClientOrderId);
        request.request = createRequestByDeleteWithSignature("/api/v3/order", builder);

        request.jsonParser = (jsonWrapper -> {
            CancelOrder result = new CancelOrder();
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setOrigClientOrderId(jsonWrapper.getString("origClientOrderId"));
            result.setOrderId(jsonWrapper.getInteger("orderId"));
            result.setOrderListId(jsonWrapper.getInteger("orderListId"));
            result.setClientOrderId(jsonWrapper.getString("clientOrderId"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            result.setOrigQty(jsonWrapper.getBigDecimal("origQty"));
            result.setExecutedQty(jsonWrapper.getBigDecimal("executedQty"));
            result.setCummulativeQuoteQty(jsonWrapper.getBigDecimal("cummulativeQuoteQty"));
            result.setStatus(jsonWrapper.getString("status"));
            result.setTimeInForce(jsonWrapper.getString("timeInForce"));
            result.setType(jsonWrapper.getString("type"));
            result.setSide(jsonWrapper.getString("side"));
            return result;
        });
        return request;
    }

    RestApiRequest<Order> getOrder(String symbol, Long orderId, String origClientOrderId) {
        RestApiRequest<Order> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("orderId", orderId)
                .putToUrl("origClientOrderId", origClientOrderId);
        request.request = createRequestByGetWithSignature("/api/v3/order", builder);

        request.jsonParser = (jsonWrapper -> {
            Order result = new Order();
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setOrderId(jsonWrapper.getInteger("orderId"));
            result.setOrderListId(jsonWrapper.getInteger("orderListId"));
            result.setClientOrderId(jsonWrapper.getString("clientOrderId"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            result.setOrigQty(jsonWrapper.getBigDecimal("origQty"));
            result.setExecutedQty(jsonWrapper.getBigDecimal("executedQty"));
            result.setCummulativeQuoteQty(jsonWrapper.getBigDecimal("cummulativeQuoteQty"));
            result.setStatus(jsonWrapper.getString("status"));
            result.setTimeInForce(jsonWrapper.getString("timeInForce"));
            result.setType(jsonWrapper.getString("type"));
            result.setSide(jsonWrapper.getString("side"));
            result.setStopPrice(jsonWrapper.getBigDecimal("stopPrice"));
            result.setIcebergQty(jsonWrapper.getBigDecimal("icebergQty"));
            result.setTime(jsonWrapper.getInteger("time"));
            result.setUpdateTime(jsonWrapper.getInteger("updateTime"));
            result.setIsWorking(jsonWrapper.getBoolean("isWorking"));
            result.setOrigQuoteOrderQty(jsonWrapper.getBigDecimal("origQuoteOrderQty"));
            return result;
        });
        return request;
    }

    RestApiRequest<List<Order>> getOpenOrders(String symbol) {
        RestApiRequest<List<Order>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGetWithSignature("/api/v3/openOrders", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Order> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Order element = new Order();
                element.setSymbol(item.getString("symbol"));
                element.setOrderId(item.getInteger("orderId"));
                element.setOrderListId(item.getInteger("orderListId"));
                element.setClientOrderId(item.getString("clientOrderId"));
                element.setPrice(item.getBigDecimal("price"));
                element.setOrigQty(item.getBigDecimal("origQty"));
                element.setExecutedQty(item.getBigDecimal("executedQty"));
                element.setCummulativeQuoteQty(item.getBigDecimal("cummulativeQuoteQty"));
                element.setStatus(item.getString("status"));
                element.setTimeInForce(item.getString("timeInForce"));
                element.setType(item.getString("type"));
                element.setSide(item.getString("side"));
                element.setStopPrice(item.getBigDecimal("stopPrice"));
                element.setIcebergQty(item.getBigDecimal("icebergQty"));
                element.setTime(item.getInteger("time"));
                element.setUpdateTime(item.getInteger("updateTime"));
                element.setIsWorking(item.getBoolean("isWorking"));
                element.setOrigQuoteOrderQty(item.getBigDecimal("origQuoteOrderQty"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<Order>> getAllOrders(String symbol, Long orderId, Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<Order>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("orderId", orderId)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGetWithSignature("/api/v3/allOrders", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Order> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Order element = new Order();
                element.setSymbol(item.getString("symbol"));
                element.setOrderId(item.getInteger("orderId"));
                element.setOrderListId(item.getInteger("orderListId"));
                element.setClientOrderId(item.getString("clientOrderId"));
                element.setPrice(item.getBigDecimal("price"));
                element.setOrigQty(item.getBigDecimal("origQty"));
                element.setExecutedQty(item.getBigDecimal("executedQty"));
                element.setCummulativeQuoteQty(item.getBigDecimal("cummulativeQuoteQty"));
                element.setStatus(item.getString("status"));
                element.setTimeInForce(item.getString("timeInForce"));
                element.setType(item.getString("type"));
                element.setSide(item.getString("side"));
                element.setStopPrice(item.getBigDecimal("stopPrice"));
                element.setIcebergQty(item.getBigDecimal("icebergQty"));
                element.setTime(item.getInteger("time"));
                element.setUpdateTime(item.getInteger("updateTime"));
                element.setIsWorking(item.getBoolean("isWorking"));
                element.setOrigQuoteOrderQty(item.getBigDecimal("origQuoteOrderQty"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<NewOco> postOco(String symbol, OrderSide side, String quantity, String price, String stopPrice, 
            String listClientOrderId, String limitClientOrderId, String limitIcebergQty,
            String stopClientOrderId, String stopLimitPrice, String stopIcebergQty, 
            TimeInForce stopLimitTimeInForce, OrderRespType newOrderRespType) {
        RestApiRequest<NewOco> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("side", side)
                .putToUrl("quantity", quantity)
                .putToUrl("price", price)
                .putToUrl("stopPrice", stopPrice)
                .putToUrl("listClientOrderId", listClientOrderId)
                .putToUrl("limitClientOrderId", limitClientOrderId)
                .putToUrl("limitIcebergQty", limitIcebergQty)
                .putToUrl("stopClientOrderId", stopClientOrderId)
                .putToUrl("stopLimitPrice", stopLimitPrice)
                .putToUrl("stopIcebergQty", stopIcebergQty)
                .putToUrl("stopLimitTimeInForce", stopLimitTimeInForce)
                .putToUrl("newOrderRespType", newOrderRespType);
        request.request = createRequestByPostWithSignature("/api/v3/order/oco", builder);

        request.jsonParser = (jsonWrapper -> {
            NewOco result = new NewOco();
            result.setOrderListId(jsonWrapper.getInteger("orderListId"));
            result.setContingencyType(jsonWrapper.getString("contingencyType"));
            result.setListStatusType(jsonWrapper.getString("listStatusType"));
            result.setListOrderStatus(jsonWrapper.getString("listOrderStatus"));
            result.setListClientOrderId(jsonWrapper.getString("listClientOrderId"));
            result.setTransactionTime(jsonWrapper.getInteger("transactionTime"));
            result.setSymbol(jsonWrapper.getString("symbol"));

            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("orders");
            List<OcoOrder> elementList = new LinkedList<>();
            dataArray.forEach((item) -> {
                OcoOrder element = new OcoOrder();
                element.setSymbol(item.getString("symbol"));
                element.setOrderId(item.getInteger("orderId"));
                element.setClientOrderId(item.getString("clientOrderId"));
                elementList.add(element);
            });
            result.setOrders(elementList);

            JsonWrapperArray reportArray = jsonWrapper.getJsonArray("orderReports");
            List<NewOcoReport> reportList = new LinkedList<>();
            reportArray.forEach((item) -> {
                NewOcoReport element = new NewOcoReport();
                element.setSymbol(item.getString("symbol"));
                element.setOrderId(item.getInteger("orderId"));
                element.setOrderListId(item.getInteger("orderListId"));
                element.setClientOrderId(item.getString("clientOrderId"));
                element.setTransactTime(item.getInteger("transactTime"));
                element.setPrice(item.getBigDecimal("price"));
                element.setOrigQty(item.getBigDecimal("origQty"));
                element.setExecutedQty(item.getBigDecimal("executedQty"));
                element.setCummulativeQuoteQty(item.getBigDecimal("cummulativeQuoteQty"));
                element.setStatus(item.getString("status"));
                element.setTimeInForce(item.getString("timeInForce"));
                element.setType(item.getString("type"));
                element.setSide(item.getString("side"));
                element.setStopPrice(item.getBigDecimal("stopPrice"));
                reportList.add(element);
            });
            result.setOrderReports(reportList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<CancelOco> cancelOco(String symbol, Long orderListId, String listClientOrderId, String newClientOrderId) {
        RestApiRequest<CancelOco> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("orderListId", orderListId)
                .putToUrl("listClientOrderId", listClientOrderId)
                .putToUrl("newClientOrderId", newClientOrderId);
        request.request = createRequestByDeleteWithSignature("/api/v3/orderList", builder);

        request.jsonParser = (jsonWrapper -> {
            CancelOco result = new CancelOco();
            result.setOrderListId(jsonWrapper.getInteger("orderListId"));
            result.setContingencyType(jsonWrapper.getString("contingencyType"));
            result.setListStatusType(jsonWrapper.getString("listStatusType"));
            result.setListOrderStatus(jsonWrapper.getString("listOrderStatus"));
            result.setListClientOrderId(jsonWrapper.getString("listClientOrderId"));
            result.setTransactionTime(jsonWrapper.getInteger("transactionTime"));
            result.setSymbol(jsonWrapper.getString("symbol"));

            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("orders");
            List<OcoOrder> elementList = new LinkedList<>();
            dataArray.forEach((item) -> {
                OcoOrder element = new OcoOrder();
                element.setSymbol(item.getString("symbol"));
                element.setOrderId(item.getInteger("orderId"));
                element.setClientOrderId(item.getString("clientOrderId"));
                elementList.add(element);
            });
            result.setOrders(elementList);

            JsonWrapperArray reportArray = jsonWrapper.getJsonArray("orderReports");
            List<CancelOcoReport> reportList = new LinkedList<>();
            reportArray.forEach((item) -> {
                CancelOcoReport element = new CancelOcoReport();
                element.setSymbol(item.getString("symbol"));
                element.setOrigClientOrderId(item.getString("origClientOrderId"));
                element.setOrderId(item.getInteger("orderId"));
                element.setOrderListId(item.getInteger("orderListId"));
                element.setClientOrderId(item.getString("clientOrderId"));
                element.setPrice(item.getBigDecimal("price"));
                element.setOrigQty(item.getBigDecimal("origQty"));
                element.setExecutedQty(item.getBigDecimal("executedQty"));
                element.setCummulativeQuoteQty(item.getBigDecimal("cummulativeQuoteQty"));
                element.setStatus(item.getString("status"));
                element.setTimeInForce(item.getString("timeInForce"));
                element.setType(item.getString("type"));
                element.setSide(item.getString("side"));
                element.setStopPrice(item.getBigDecimal("stopPrice"));
                reportList.add(element);
            });
            result.setOrderReports(reportList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<Oco> getOco(Long orderListId, String origClientOrderId) {
        RestApiRequest<Oco> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("orderListId", orderListId)
                .putToUrl("origClientOrderId", origClientOrderId);
        request.request = createRequestByGetWithSignature("/api/v3/orderList", builder);

        request.jsonParser = (jsonWrapper -> {
            Oco result = new Oco();
            result.setOrderListId(jsonWrapper.getInteger("orderListId"));
            result.setContingencyType(jsonWrapper.getString("contingencyType"));
            result.setListStatusType(jsonWrapper.getString("listStatusType"));
            result.setListOrderStatus(jsonWrapper.getString("listOrderStatus"));
            result.setListClientOrderId(jsonWrapper.getString("listClientOrderId"));
            result.setTransactionTime(jsonWrapper.getInteger("transactionTime"));
            result.setSymbol(jsonWrapper.getString("symbol"));

            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("orders");
            List<OcoOrder> elementList = new LinkedList<>();
            dataArray.forEach((item) -> {
                OcoOrder element = new OcoOrder();
                element.setSymbol(item.getString("symbol"));
                element.setOrderId(item.getInteger("orderId"));
                element.setClientOrderId(item.getString("clientOrderId"));
                elementList.add(element);
            });
            result.setOrders(elementList);
            
            return result;
        });
        return request;
    }

    RestApiRequest<List<Oco>> getAllOco(Long fromId, Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<Oco>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("fromId", fromId)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGetWithSignature("/api/v3/allOrderList", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Oco> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Oco element = new Oco();
                element.setOrderListId(item.getInteger("orderListId"));
                element.setContingencyType(item.getString("contingencyType"));
                element.setListStatusType(item.getString("listStatusType"));
                element.setListOrderStatus(item.getString("listOrderStatus"));
                element.setListClientOrderId(item.getString("listClientOrderId"));
                element.setTransactionTime(item.getInteger("transactionTime"));
                element.setSymbol(item.getString("symbol"));
                
                List<OcoOrder> orderList = new LinkedList<>();
                JsonWrapperArray orderArray = item.getJsonArray("orders");
                orderArray.forEach((val) -> {
                    OcoOrder order = new OcoOrder();
                    order.setSymbol(item.getString("symbol"));
                    order.setOrderId(item.getInteger("orderId"));
                    order.setClientOrderId(item.getString("clientOrderId"));
                    orderList.add(order);
                });
                element.setOrders(orderList);
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<Oco>> getOpenOco() {
        RestApiRequest<List<Oco>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/api/v3/openOrderList", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Oco> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Oco element = new Oco();
                element.setOrderListId(item.getInteger("orderListId"));
                element.setContingencyType(item.getString("contingencyType"));
                element.setListStatusType(item.getString("listStatusType"));
                element.setListOrderStatus(item.getString("listOrderStatus"));
                element.setListClientOrderId(item.getString("listClientOrderId"));
                element.setTransactionTime(item.getInteger("transactionTime"));
                element.setSymbol(item.getString("symbol"));
                
                List<OcoOrder> orderList = new LinkedList<>();
                JsonWrapperArray orderArray = item.getJsonArray("orders");
                orderArray.forEach((val) -> {
                    OcoOrder order = new OcoOrder();
                    order.setSymbol(item.getString("symbol"));
                    order.setOrderId(item.getInteger("orderId"));
                    order.setClientOrderId(item.getString("clientOrderId"));
                    orderList.add(order);
                });
                element.setOrders(orderList);
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<AccountInformation> getAccountInformation() {
        RestApiRequest<AccountInformation> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/api/v3/account", builder);

        request.jsonParser = (jsonWrapper -> {
            AccountInformation result = new AccountInformation();
            result.setMakerCommission(jsonWrapper.getInteger("makerCommission"));
            result.setTakerCommission(jsonWrapper.getInteger("takerCommission"));
            result.setBuyerCommission(jsonWrapper.getInteger("buyerCommission"));
            result.setSellerCommission(jsonWrapper.getInteger("sellerCommission"));
            result.setCanTrade(jsonWrapper.getBoolean("canTrade"));
            result.setCanWithdraw(jsonWrapper.getBoolean("canWithdraw"));
            result.setCanDeposit(jsonWrapper.getBoolean("canDeposit"));
            result.setUpdateTime(jsonWrapper.getInteger("updateTime"));
            result.setAccountType(jsonWrapper.getString("accountType"));

            List<Balance> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("balances");
            dataArray.forEach((item) -> {
                Balance element = new Balance();
                element.setAsset(item.getString("asset"));
                element.setFree(item.getBigDecimal("free"));
                element.setLocked(item.getBigDecimal("locked"));
                elementList.add(element);
            });
            result.setBalances(elementList);
            return result;
        });
        return request;
    }

    RestApiRequest<List<MyTrade>> getAccountTrades(String symbol, Long startTime, Long endTime, 
            Long fromId, Integer limit) {
        RestApiRequest<List<MyTrade>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("fromId", fromId)
                .putToUrl("limit", limit);
        request.request = createRequestByGetWithSignature("/api/v3/myTrades", builder);

        request.jsonParser = (jsonWrapper -> {
            List<MyTrade> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                MyTrade element = new MyTrade();
                element.setSymbol(item.getString("symbol"));
                element.setId(item.getInteger("id"));
                element.setOrderId(item.getInteger("orderId"));
                element.setOrderListId(item.getInteger("orderListId"));
                element.setPrice(item.getBigDecimal("price"));
                element.setQty(item.getBigDecimal("qty"));
                element.setQuoteQty(item.getBigDecimal("quoteQty"));
                element.setCommission(item.getBigDecimal("commission"));
                element.setCommissionAsset(item.getString("commissionAsset"));
                element.setTime(item.getInteger("time"));
                element.setIsBuyer(item.getBoolean("isBuyer"));
                element.setIsMaker(item.getBoolean("isMaker"));
                element.setIsBestMatch(item.getBoolean("isBestMatch"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<MarginAsset> getMarginAsset(String asset) {
        RestApiRequest<MarginAsset> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset);
        request.request = createRequestByGetWithApikey("/sapi/v1/margin/asset", builder);

        request.jsonParser = (jsonWrapper -> {
            MarginAsset result = new MarginAsset();
            result.setAssetFullName(jsonWrapper.getString("assetFullName"));
            result.setAssetName(jsonWrapper.getString("assetName"));
            result.setIsBorrowable(jsonWrapper.getBoolean("isBorrowable"));
            result.setIsMortgageable(jsonWrapper.getBoolean("isMortgageable"));
            result.setUserMinBorrow(jsonWrapper.getBigDecimal("userMinBorrow"));
            result.setUserMinRepay(jsonWrapper.getBigDecimal("userMinRepay"));
            return result;
        });
        return request;
    }

    RestApiRequest<Long> postMarginTransfer(String asset, String amount, MarginTransferType type) {
        RestApiRequest<Long> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset)
                .putToUrl("amount", amount)
                .putToUrl("type", type);
        request.request = createRequestByPostWithSignature("/sapi/v1/margin/transfer", builder);

        request.jsonParser = (jsonWrapper -> {
            Long result = jsonWrapper.getLong("tranId");
            return result;
        });
        return request;
    }

    RestApiRequest<Long> postMarginBorrow(String asset, String amount) {
        RestApiRequest<Long> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset)
                .putToUrl("amount", amount);
        request.request = createRequestByPostWithSignature("/sapi/v1/margin/loan", builder);

        request.jsonParser = (jsonWrapper -> {
            Long result = jsonWrapper.getLong("tranId");
            return result;
        });
        return request;
    }

    RestApiRequest<Long> postMarginRepay(String asset, String amount) {
        RestApiRequest<Long> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("asset", asset)
                .putToUrl("amount", amount);
        request.request = createRequestByPostWithSignature("/sapi/v1/margin/repay", builder);

        request.jsonParser = (jsonWrapper -> {
            Long result = jsonWrapper.getLong("tranId");
            return result;
        });
        return request;
    }

    RestApiRequest<MarginPair> getMarginPair(String symbol) {
        RestApiRequest<MarginPair> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGetWithApikey("/sapi/v1/margin/pair", builder);

        request.jsonParser = (jsonWrapper -> {
            MarginPair result = new MarginPair();
            result.setId(jsonWrapper.getInteger("id"));
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setBase(jsonWrapper.getString("base"));
            result.setQuote(jsonWrapper.getString("quote"));
            result.setIsMarginTrade(jsonWrapper.getBoolean("isMarginTrade"));
            result.setIsBuyAllowed(jsonWrapper.getBoolean("isBuyAllowed"));
            result.setIsSellAllowed(jsonWrapper.getBoolean("isSellAllowed"));
            return result;
        });
        return request;
    }

    RestApiRequest<List<MarginAsset>> getMarginAssets() {
        RestApiRequest<List<MarginAsset>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithApikey("/sapi/v1/margin/allAssets", builder);

        request.jsonParser = (jsonWrapper -> {
            List<MarginAsset> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");

            dataArray.forEach((item) -> {
                MarginAsset element = new MarginAsset();
                element.setAssetFullName(item.getString("assetFullName"));
                element.setAssetName(item.getString("assetName"));
                element.setIsBorrowable(item.getBoolean("isBorrowable"));
                element.setIsMortgageable(item.getBoolean("isMortgageable"));
                element.setUserMinBorrow(item.getBigDecimal("userMinBorrow"));
                element.setUserMinRepay(item.getBigDecimal("userMinRepay"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<List<MarginPair>> getMarginPairs() {
        RestApiRequest<List<MarginPair>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithApikey("/sapi/v1/margin/allPairs", builder);

        request.jsonParser = (jsonWrapper -> {
            List<MarginPair> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");

            dataArray.forEach((item) -> {
                MarginPair element = new MarginPair();
                element.setId(item.getInteger("id"));
                element.setSymbol(item.getString("symbol"));
                element.setBase(item.getString("base"));
                element.setQuote(item.getString("quote"));
                element.setIsMarginTrade(item.getBoolean("isMarginTrade"));
                element.setIsBuyAllowed(item.getBoolean("isBuyAllowed"));
                element.setIsSellAllowed(item.getBoolean("isSellAllowed"));
                result.add(element);
            });
            
            return result;
        });
        return request;
    }

    RestApiRequest<MarginPriceIndex> getMarginPriceIndex(String symbol) {
        RestApiRequest<MarginPriceIndex> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGetWithApikey("/sapi/v1/margin/priceIndex", builder);

        request.jsonParser = (jsonWrapper -> {
            MarginPriceIndex result = new MarginPriceIndex();
            result.setCalcTime(jsonWrapper.getInteger("calcTime"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            result.setSymbol(jsonWrapper.getString("symbol"));
            return result;
        });
        return request;
    }

    RestApiRequest<MarginNewOrder> postMarginOrder(String symbol, OrderSide side, OrderType orderType,
            String quantity, String price, String quoteOrderQty, String stopPrice,
            String newClientOrderId, String icebergQty, OrderRespType newOrderRespType, 
            SideEffectType sideEffectType,  TimeInForce timeInForce) {
        RestApiRequest<MarginNewOrder> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("side", side)
                .putToUrl("type", orderType)
                .putToUrl("quantity", quantity)
                .putToUrl("price", price)
                .putToUrl("quoteOrderQty", quoteOrderQty)
                .putToUrl("stopPrice", stopPrice)
                .putToUrl("newClientOrderId", newClientOrderId)
                .putToUrl("icebergQty", icebergQty)
                .putToUrl("newOrderRespType", newOrderRespType)
                .putToUrl("sideEffectType", sideEffectType)
                .putToUrl("timeInForce", timeInForce);
        request.request = createRequestByPostWithSignature("/sapi/v1/margin/order", builder);

        request.jsonParser = (jsonWrapper -> {
            MarginNewOrder result = new MarginNewOrder();
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setOrderId(jsonWrapper.getInteger("orderId"));
            result.setOrderListId(jsonWrapper.getInteger("orderListId"));
            result.setClientOrderId(jsonWrapper.getString("clientOrderId"));
            result.setTransactTime(jsonWrapper.getInteger("transactTime"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            result.setOrigQty(jsonWrapper.getBigDecimal("origQty"));
            result.setExecutedQty(jsonWrapper.getBigDecimal("executedQty"));
            result.setCummulativeQuoteQty(jsonWrapper.getBigDecimal("cummulativeQuoteQty"));
            result.setStatus(jsonWrapper.getString("status"));
            result.setTimeInForce(jsonWrapper.getString("timeInForce"));
            result.setType(jsonWrapper.getString("type"));
            result.setSide(jsonWrapper.getString("side"));
            result.setMarginBuyBorrowAmount(jsonWrapper.getString("marginBuyBorrowAmount"));
            result.setMarginBuyBorrowAsset(jsonWrapper.getString("marginBuyBorrowAsset"));

            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("fills");
            List<Fill> elementList = new LinkedList<>();
            dataArray.forEach((item) -> {
                Fill element = new Fill();
                element.setPrice(item.getBigDecimal("price"));
                element.setQty(item.getBigDecimal("qty"));
                element.setCommission(item.getBigDecimal("commission"));
                element.setCommissionAsset(item.getString("commissionAsset"));
                elementList.add(element);
            });
            result.setFills(elementList);
            
            return result;
        });
        return request;
    }

}
