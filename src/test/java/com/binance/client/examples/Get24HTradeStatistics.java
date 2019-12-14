package com.binance.client.examples;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.model.TradeStatistics;

public class Get24HTradeStatistics {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        // Synchronization mode
        SyncRequestClient syncRequestClient = SyncRequestClient.create("", "", options);
        TradeStatistics statistics = syncRequestClient.get24HTradeStatistics("BTCUSDT");
        System.out.println("---- Statistics ----");
        System.out.println("Timestamp: " + statistics.getTimestamp());
        System.out.println("High: " + statistics.getHigh());
        System.out.println("Low: " + statistics.getLow());
        System.out.println("Open: " + statistics.getOpen());
        System.out.println("Close: " + statistics.getClose());
        System.out.println("Volume: " + statistics.getVolume());
        System.out.println();
    }
}
