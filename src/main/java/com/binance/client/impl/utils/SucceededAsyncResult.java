package com.binance.client.impl.utils;

import com.binance.client.AsyncResult;
import com.binance.client.exception.HuobiApiException;

public class SucceededAsyncResult<T> implements AsyncResult<T> {

  private final T data;

  public SucceededAsyncResult(T data) {
    this.data = data;
  }

  @Override
  public HuobiApiException getException() {
    return null;
  }

  @Override
  public boolean succeeded() {
    return true;
  }

  @Override
  public T getData() {
    return data;
  }
}
