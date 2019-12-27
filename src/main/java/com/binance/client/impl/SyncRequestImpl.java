package com.binance.client.impl;

import com.binance.client.SyncRequestClient;
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
import com.binance.client.model.wallet.DustTransfer;
import com.binance.client.model.wallet.FuturesSummary;
import com.binance.client.model.wallet.MarginSummary;
import com.binance.client.model.wallet.SubAccount;
import com.binance.client.model.wallet.SubAccountDepositHistory;
import com.binance.client.model.wallet.SubAccountFuturesDetail;
import com.binance.client.model.wallet.SubAccountFuturesPositionRisk;
import com.binance.client.model.wallet.SubAccountStatus;
import com.binance.client.model.wallet.SubAccountTransferHistory;
import com.binance.client.model.wallet.SubAccountMarginDetail;
import com.binance.client.model.wallet.SystemStatus;
import com.binance.client.model.wallet.TradeFee;
import com.binance.client.model.wallet.TradeStatistics;
import com.binance.client.model.wallet.WithdrawHistory;
import com.binance.client.model.wallet.WithdrawHistorySapi;
import com.binance.client.model.market.AggregateTrade;
import com.binance.client.model.market.AveragePrice;
import com.binance.client.model.market.Candlestick;
import com.binance.client.model.market.ExchangeInformation;
import com.binance.client.model.market.OrderBook;
import com.binance.client.model.market.PriceChangeTicker;
import com.binance.client.model.market.SymbolOrderBook;
import com.binance.client.model.market.SymbolPrice;
import com.binance.client.model.market.Trade;
import com.binance.client.model.spot.AccountInformation;
import com.binance.client.model.spot.CancelOco;
import com.binance.client.model.spot.CancelOrder;
import com.binance.client.model.spot.MyTrade;
import com.binance.client.model.spot.NewOco;
import com.binance.client.model.spot.NewOrder;
import com.binance.client.model.spot.Oco;
import com.binance.client.model.spot.Order;
import com.binance.client.model.enums.*;
import com.binance.client.model.margin.MarginAsset;
import com.binance.client.model.margin.MarginPair;
import com.binance.client.model.margin.MarginPriceIndex;

import java.util.List;

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

    @Override
    public List<DepositHistorySapi> getDepositHistorySapi(String coin, Integer status, Long startTime, Long endTime, Integer offset) {
        return RestApiInvoker.callSync(requestImpl.getDepositHistorySapi(coin, status, startTime, endTime, offset));
    }

    @Override
    public List<DepositHistory> getDepositHistory(String asset, Integer status, Long startTime, Long endTime) {
        return RestApiInvoker.callSync(requestImpl.getDepositHistory(asset, status, startTime, endTime));
    }

    @Override
    public List<WithdrawHistorySapi> getWithdrawHistorySapi(String coin, Integer status, Integer offset, 
            Integer limit, Long startTime, Long endTime) {
        return RestApiInvoker.callSync(requestImpl.getWithdrawHistorySapi(coin, status, offset, limit, startTime, endTime));
    }

    @Override
    public List<WithdrawHistory> getWithdrawHistory(String asset, Integer status, Long startTime, Long endTime) {
        return RestApiInvoker.callSync(requestImpl.getWithdrawHistory(asset, status, startTime, endTime));
    }

    @Override
    public DepositAddressSapi getDepositAddressSapi(String coin, String network) {
        return RestApiInvoker.callSync(requestImpl.getDepositAddressSapi(coin, network));
    }

    @Override
    public DepositAddress getDepositAddress(String asset, String status) {
        return RestApiInvoker.callSync(requestImpl.getDepositAddress(asset, status));
    }

    @Override
    public AccountStatus getAccountStatus() {
        return RestApiInvoker.callSync(requestImpl.getAccountStatus());
    }

    @Override
    public AccountApiTradingStatus getAccountApiTradingStatus() {
        return RestApiInvoker.callSync(requestImpl.getAccountApiTradingStatus());
    }

    @Override
    public List<DustLog> getDustLog() {
        return RestApiInvoker.callSync(requestImpl.getDustLog());
    }

    @Override
    public List<TradeFee> getTradeFee(String symbol) {
        return RestApiInvoker.callSync(requestImpl.getTradeFee(symbol));
    }

    @Override
    public List<AssetDetail> getAssetDetail() {
        return RestApiInvoker.callSync(requestImpl.getAssetDetail());
    }

    @Override
    public List<SubAccount> getSubAccounts(String email, String status, Integer page, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getSubAccounts(email, status, page, limit));
    }

    @Override
    public List<SubAccountTransferHistory> getSubAccountTransferHistory(String email, Long startTime, Long endTime,
            Integer page, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getSubAccountTransferHistory(email, startTime, endTime, page, limit));
    }

    @Override
    public Long postSubAccountTransfer(String fromEmail, String toEmail, String asset, String amount) {
        return RestApiInvoker.callSync(requestImpl.postSubAccountTransfer(fromEmail, toEmail, asset, amount));
    }

    @Override
    public List<Balance> getSubAccountAssets(String email, String symbol) {
        return RestApiInvoker.callSync(requestImpl.getSubAccountAssets(email, symbol));
    }

    @Override
    public DepositAddressSapi getSubAccountDepositAddress(String email, String coin, String network) {
        return RestApiInvoker.callSync(requestImpl.getSubAccountDepositAddress(email, coin, network));
    }

    @Override
    public List<SubAccountDepositHistory> getSubAccountDepositHistory(String email, String coin, Integer status,
            Long startTime, Long endTime, Integer limit, Integer offset) {
        return RestApiInvoker.callSync(requestImpl.getSubAccountDepositHistory(email, coin, status, 
                startTime, endTime, limit, offset));
    }

    @Override
    public List<SubAccountStatus> getSubAccountStatus(String email) {
        return RestApiInvoker.callSync(requestImpl.getSubAccountStatus(email));
    }

    @Override
    public Boolean postEnableMargin(String email) {
        return RestApiInvoker.callSync(requestImpl.postEnableMargin(email));
    }

    @Override
    public SubAccountMarginDetail getSubAccountMarginDetail(String email) {
        return RestApiInvoker.callSync(requestImpl.getSubAccountMarginDetail(email));
    }

    @Override
    public MarginSummary getSubAccountMarginSummary() {
        return RestApiInvoker.callSync(requestImpl.getSubAccountMarginSummary());
    }

    @Override
    public Boolean postEnableFutures(String email) {
        return RestApiInvoker.callSync(requestImpl.postEnableFutures(email));
    }
    
    @Override
    public SubAccountFuturesDetail getSubAccountFuturesDetail(String email) {
        return RestApiInvoker.callSync(requestImpl.getSubAccountFuturesDetail(email));
    }
    
    @Override
    public FuturesSummary getSubAccountFuturesSummary() {
        return RestApiInvoker.callSync(requestImpl.getSubAccountFuturesSummary());
    }
    
    @Override
    public List<SubAccountFuturesPositionRisk> getSubAccountFuturesPositionRisk(String email) {
        return RestApiInvoker.callSync(requestImpl.getSubAccountFuturesPositionRisk(email));
    }
    
    @Override
    public DustTransfer postDustTransfer(String asset) {
        return RestApiInvoker.callSync(requestImpl.postDustTransfer(asset));
    }
    
    @Override
    public List<AssetDividendRecord> getAssetDividendRecord(String asset, Long startTime, Long endTime) {
        return RestApiInvoker.callSync(requestImpl.getAssetDividendRecord(asset, startTime, endTime));
    }
    
    @Override
    public ExchangeInformation getExchangeInformation() {
        return RestApiInvoker.callSync(requestImpl.getExchangeInformation());
    }
    
    @Override
    public OrderBook getOrderBook(String symbol, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getOrderBook(symbol, limit));
    }
    
    @Override
    public List<Trade> getRecentTrades(String symbol, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getRecentTrades(symbol, limit));
    }
    
    @Override
    public List<Trade> getOldTrades(String symbol, Integer limit, Long fromId) {
        return RestApiInvoker.callSync(requestImpl.getOldTrades(symbol, limit, fromId));
    }
    
    @Override
    public List<AggregateTrade> getAggregateTrades(String symbol, Long fromId, Long startTime, 
            Long endTime, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getAggregateTrades(symbol, fromId, startTime, endTime, limit));
    }
    
    @Override
    public List<Candlestick> getCandlestick(String symbol, CandlestickInterval interval, Long startTime, 
            Long endTime, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getCandlestick(symbol, interval, startTime, endTime, limit));
    }
    
    @Override
    public AveragePrice getCurrentAveragePrice(String symbol) {
        return RestApiInvoker.callSync(requestImpl.getCurrentAveragePrice(symbol));
    }
    
    @Override
    public List<PriceChangeTicker> get24hrTickerPriceChange(String symbol) {
        return RestApiInvoker.callSync(requestImpl.get24hrTickerPriceChange(symbol));
    }
    
    @Override
    public List<SymbolPrice> getSymbolPriceTicker(String symbol) {
        return RestApiInvoker.callSync(requestImpl.getSymbolPriceTicker(symbol));
    }
    
    @Override
    public List<SymbolOrderBook> getSymbolOrderBookTicker(String symbol) {
        return RestApiInvoker.callSync(requestImpl.getSymbolOrderBookTicker(symbol));
    }
    
    @Override
    public NewOrder postOrder(String symbol, OrderSide side, OrderType orderType,
            TimeInForce timeInForce, String quantity, String price, String quoteOrderQty, 
            String newClientOrderId, String stopPrice, String icebergQty, OrderRespType newOrderRespType) {
        return RestApiInvoker.callSync(requestImpl.postOrder(symbol, side, orderType, 
                timeInForce, quantity, price, quoteOrderQty, 
                newClientOrderId, stopPrice, icebergQty, newOrderRespType));
    }
    
    @Override
    public CancelOrder cancelOrder(String symbol, Long orderId, String origClientOrderId, String newClientOrderId) {
        return RestApiInvoker.callSync(requestImpl.cancelOrder(symbol, orderId, origClientOrderId, newClientOrderId));
    }
    
    @Override
    public Order getOrder(String symbol, Long orderId, String origClientOrderId) {
        return RestApiInvoker.callSync(requestImpl.getOrder(symbol, orderId, origClientOrderId));
    }
    
    @Override
    public List<Order> getOpenOrders(String symbol) {
        return RestApiInvoker.callSync(requestImpl.getOpenOrders(symbol));
    }
    
    @Override
    public List<Order> getAllOrders(String symbol, Long orderId, Long startTime, Long endTime, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getAllOrders(symbol, orderId, startTime, endTime, limit));
    }
    
    @Override
    public NewOco postOco(String symbol, OrderSide side, String quantity, String price, String stopPrice, 
        String listClientOrderId, String limitClientOrderId, String limitIcebergQty,
        String stopClientOrderId, String stopLimitPrice, String stopIcebergQty, 
        TimeInForce stopLimitTimeInForce, OrderRespType newOrderRespType) {
        return RestApiInvoker.callSync(requestImpl.postOco(symbol, side, quantity, price, stopPrice, 
                listClientOrderId, limitClientOrderId, limitIcebergQty,
                stopClientOrderId, stopLimitPrice, stopIcebergQty, 
                stopLimitTimeInForce, newOrderRespType));
    }
    
    @Override
    public CancelOco cancelOco(String symbol, Long orderListId, String listClientOrderId, String newClientOrderId) {
        return RestApiInvoker.callSync(requestImpl.cancelOco(symbol, orderListId, listClientOrderId, newClientOrderId));
    }
    
    @Override
    public Oco getOco(Long orderListId, String origClientOrderId) {
        return RestApiInvoker.callSync(requestImpl.getOco(orderListId, origClientOrderId));
    }
    
    @Override
    public List<Oco> getAllOco(Long fromId, Long startTime, Long endTime, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getAllOco(fromId, startTime, endTime, limit));
    }
    
    @Override
    public List<Oco> getOpenOco() {
        return RestApiInvoker.callSync(requestImpl.getOpenOco());
    }
    
    @Override
    public AccountInformation getAccountInformation() {
        return RestApiInvoker.callSync(requestImpl.getAccountInformation());
    }
    
    @Override
    public List<MyTrade> getAccountTrades(String symbol, Long startTime, Long endTime, Long fromId, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getAccountTrades(symbol, startTime, endTime, fromId, limit));
    }
    
    @Override
    public MarginAsset getMarginAsset(String asset) {
        return RestApiInvoker.callSync(requestImpl.getMarginAsset(asset));
    }
    
    @Override
    public Long postMarginTransfer(String asset, String amount, MarginTransferType type) {
        return RestApiInvoker.callSync(requestImpl.postMarginTransfer(asset, amount, type));
    }
    
    @Override
    public Long postMarginBorrow(String asset, String amount) {
        return RestApiInvoker.callSync(requestImpl.postMarginBorrow(asset, amount));
    }
    
    @Override
    public Long postMarginRepay(String asset, String amount) {
        return RestApiInvoker.callSync(requestImpl.postMarginRepay(asset, amount));
    }
    
    @Override
    public MarginPair getMarginPair(String symbol) {
        return RestApiInvoker.callSync(requestImpl.getMarginPair(symbol));
    }
    
    @Override
    public List<MarginAsset> getMarginAssets() {
        return RestApiInvoker.callSync(requestImpl.getMarginAssets());
    }
    
    @Override
    public List<MarginPair> getMarginPairs() {
        return RestApiInvoker.callSync(requestImpl.getMarginPairs());
    }
    
    @Override
    public MarginPriceIndex getMarginPriceIndex(String symbol) {
        return RestApiInvoker.callSync(requestImpl.getMarginPriceIndex(symbol));
    }

}
