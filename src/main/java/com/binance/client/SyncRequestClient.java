package com.binance.client;

import com.binance.client.impl.BinanceApiInternalFactory;
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
import com.binance.client.model.market.Candlestick;
import com.binance.client.model.market.ExchangeInformation;
import com.binance.client.model.market.OrderBook;
import com.binance.client.model.market.Trade;
import com.binance.client.model.enums.*;

import java.util.List;

/**
 * Synchronous request interface, invoking Huobi RestAPI via synchronous
 * method.<br>
 * All methods in this interface will be blocked until the RestAPI response.
 * <p>
 * If the invoking failed or timeout, the
 * {@link com.binance.client.exception.BinanceApiException} will be thrown.
 */
public interface SyncRequestClient {

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create() {
        return create("", "", new RequestOptions());
    }

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @param apiKey    The public key applied from binance.
     * @param secretKey The private key applied from binance.
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create(String apiKey, String secretKey) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, new RequestOptions());
    }

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @param apiKey    The public key applied from binance.
     * @param secretKey The private key applied from binance.
     * @param options   The request option.
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create(String apiKey, String secretKey, RequestOptions options) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, options);
    }

    /**
     * Get trade statistics in 24 hours.
     *
     * @param symbol The symbol, like "btcusdt". (mandatory)
     * @return Trade statistics, see {@link TradeStatistics}
     */
    TradeStatistics get24HTradeStatistics(String symbol);

    /**
     * Fetch system status.
     *
     * @return System status.
     */
    SystemStatus getSystemStatus();

    /**
     * Get all coins' information for user.
     *
     * @return All coins' information.
     */
    List<CoinInformation> getAllCoinsInformation();

    /**
     * Submit a withdraw request.
     *
     * @return Transaction ID.
     */
    Long postWithdrawSapi(String coin, String address, String amount, String network, String addressTag, String name);

    /**
     * Submit a withdraw request.
     *
     * @return Transaction ID.
     */
    Long postWithdraw(String asset, String address, String amount, String network, String addressTag, String name);

    /**
     * Fetch deposit history.
     *
     * @return Deposit history.
     */
    List<DepositHistorySapi> getDepositHistorySapi(String coin, Integer status, Long startTime, Long endTime, Integer offset);

    /**
     * Fetch deposit history.
     *
     * @return Deposit history.
     */
    List<DepositHistory> getDepositHistory(String asset, Integer status, Long startTime, Long endTime);

    /**
     * Fetch withdraw history.
     *
     * @return Withdraw history.
     */
    List<WithdrawHistorySapi> getWithdrawHistorySapi(String coin, Integer status, Integer offset, 
            Integer limit, Long startTime, Long endTime);

    /**
     * Fetch withdraw history.
     *
     * @return Withdraw history.
     */
    List<WithdrawHistory> getWithdrawHistory(String asset, Integer status, Long startTime, Long endTime);

    /**
     * Fetch deposit address with network.
     *
     * @return Deposit address.
     */
    DepositAddressSapi getDepositAddressSapi(String coin, String network);

    /**
     * Fetch deposit address with network.
     *
     * @return Deposit address.
     */
    DepositAddress getDepositAddress(String asset, String status);

    /**
     * Fetch account status detail.
     *
     * @return Account status detail.
     */
    AccountStatus getAccountStatus();

    /**
     * Fetch account api trading status detail.
     *
     * @return Account api trading status detail.
     */
    AccountApiTradingStatus getAccountApiTradingStatus();

    /**
     * Fetch small amounts of assets exchanged BNB records.
     *
     * @return Small amounts of assets exchanged BNB records.
     */
    List<DustLog> getDustLog();

    /**
     * Fetch trade fee.
     *
     * @return Trade fee.
     */
    List<TradeFee> getTradeFee(String symbol);

    /**
     * Fetch asset detail.
     *
     * @return Asset detail.
     */
    List<AssetDetail> getAssetDetail();

    /**
     * Fetch sub account list.
     *
     * @return Sub account list.
     */
    List<SubAccount> getSubAccounts(String email, String status, Integer page, Integer limit);

    /**
     * Fetch transfer history list.
     *
     * @return Transfer history.
     */
    List<SubAccountTransferHistory> getSubAccountTransferHistory(String email, Long startTime, Long endTime,
            Integer page, Integer limit);

    /**
     * Execute sub-account transfer.
     *
     * @return Transaction ID.
     */
    Long postSubAccountTransfer(String fromEmail, String toEmail, String asset, String amount);

    /**
     * Fetch sub-account assets.
     *
     * @return Sub-account assets.
     */
    List<Balance> getSubAccountAssets(String email, String symbol);

    /**
     * Fetch sub-account deposit address.
     *
     * @return Sub-account deposit address.
     */
    DepositAddressSapi getSubAccountDepositAddress(String email, String coin, String network);

    /**
     * Fetch sub-account deposit history.
     *
     * @return Sub-account deposit history.
     */
    List<SubAccountDepositHistory> getSubAccountDepositHistory(String email, String coin, Integer status,
            Long startTime, Long endTime, Integer limit, Integer offset);

    /**
     * Fetch sub-account status.
     *
     * @return Sub-account status.
     */
    List<SubAccountStatus> getSubAccountStatus(String email);

    /**
     * Enable Margin for Sub-account.
     *
     * @return Sub-account margin status.
     */
    Boolean postEnableMargin(String email);

    /**
     * Get Detail on Sub-account's Margin Account.
     *
     * @return Detail on Sub-account's Margin Account.
     */
    SubAccountMarginDetail getSubAccountMarginDetail(String email);

    /**
     * Get Summary of Sub-account's Margin Account.
     *
     * @return Summary of Sub-account's Margin Account.
     */
    MarginSummary getSubAccountMarginSummary();

    /**
     * Enable Futures for Sub-account.
     *
     * @return Sub-account futures status.
     */
    Boolean postEnableFutures(String email);

    /**
     * Get Detail on Sub-account's Futures Account.
     *
     * @return Detail on Sub-account's Futures Account.
     */
    SubAccountFuturesDetail getSubAccountFuturesDetail(String email);

    /**
     * Get Summary of Sub-account's Futures Account.
     *
     * @return Summary of Sub-account's Futures Account.
     */
    FuturesSummary getSubAccountFuturesSummary();

    /**
     * Get Futures Postion-Risk of Sub-account.
     *
     * @return Futures Postion-Risk of Sub-account.
     */
    List<SubAccountFuturesPositionRisk> getSubAccountFuturesPositionRisk(String email);

    /**
     * Convert dust assets to BNB.
     *
     * @return Transfer result.
     */
    DustTransfer postDustTransfer(String asset);

    /**
     * Query asset dividend record.
     *
     * @return Asset dividend record.
     */
    List<AssetDividendRecord> getAssetDividendRecord(String asset, Long startTime, Long endTime);

    /**
     * Fetch current exchange trading rules and symbol information.
     *
     * @return Current exchange trading rules and symbol information.
     */
    ExchangeInformation getExchangeInformation();

    /**
     * Fetch order book.
     *
     * @return Order book.
     */
    OrderBook getOrderBook(String symbol, Integer limit);

    /**
     * Get recent trades.
     *
     * @return Recent trades.
     */
    List<Trade> getRecentTrades(String symbol, Integer limit);

    /**
     * Get old Trade.
     *
     * @return Old trades.
     */
    List<Trade> getOldTrades(String symbol, Integer limit, Long fromId);

    /**
     * Get compressed, aggregate trades.
     *
     * @return Aggregate trades.
     */
    List<AggregateTrade> getAggregateTrades(String symbol, Long fromId, Long startTime, Long endTime, Integer limit);

    /**
     * Get kline/candlestick bars for a symbol.
     *
     * @return Kline/candlestick bars for a symbol.
     */
    List<Candlestick> getCandlestick(String symbol, CandlestickInterval interval, Long startTime, Long endTime, Integer limit);

}
