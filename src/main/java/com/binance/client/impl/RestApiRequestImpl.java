package com.binance.client.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import com.binance.client.RequestOptions;
import com.binance.client.exception.BinanceApiException;
import com.binance.client.impl.utils.JsonWrapper;
import com.binance.client.impl.utils.JsonWrapperArray;
import com.binance.client.impl.utils.UrlParamsBuilder;
import com.binance.client.model.AccountApiTradingStatus;
import com.binance.client.model.AccountStatus;
import com.binance.client.model.AssetDetail;
import com.binance.client.model.CoinInformation;
import com.binance.client.model.DepositAddress;
import com.binance.client.model.DepositAddressSapi;
import com.binance.client.model.DepositHistory;
import com.binance.client.model.DepositHistorySapi;
import com.binance.client.model.DustLog;
import com.binance.client.model.DustLogEntry;
import com.binance.client.model.Indicator;
import com.binance.client.model.IndicatorInfo;
import com.binance.client.model.Network;
import com.binance.client.model.SystemStatus;
import com.binance.client.model.TradeFee;
import com.binance.client.model.TradeStatistics;
import com.binance.client.model.TriggerCondition;
import com.binance.client.model.WithdrawHistory;
import com.binance.client.model.WithdrawHistorySapi;

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

}
