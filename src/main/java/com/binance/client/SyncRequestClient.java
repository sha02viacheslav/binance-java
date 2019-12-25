package com.binance.client;

import com.binance.client.impl.BinanceApiInternalFactory;
import com.binance.client.model.SystemStatus;
import com.binance.client.model.TradeStatistics;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

}
