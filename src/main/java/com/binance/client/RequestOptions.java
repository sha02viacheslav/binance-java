package com.binance.client;

import com.binance.client.exception.BinanceApiException;
import java.net.URL;

/**
 * The configuration for the request APIs
 */
public class RequestOptions {

  private String url = "https://api.binance.com";

  public RequestOptions() {
  }

  public RequestOptions(RequestOptions option) {
    this.url = option.url;
  }

  /**
   * Set the URL for request.
   *
   * @param url The URL name like "https://api.huobi.pro".
   */
  public void setUrl(String url) {
    try {
      URL u = new URL(url);
    } catch (Exception e)
    {
      throw new BinanceApiException(
          BinanceApiException.INPUT_ERROR, "The URI is incorrect: " + e.getMessage());
    }
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}