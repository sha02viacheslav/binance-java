package com.binance.client.impl.utils;

import com.binance.client.AsyncResult;
import com.binance.client.exception.HuobiApiException;

public class FailedAsyncResult<T> implements AsyncResult<T> {

  private final HuobiApiException exception;

  public FailedAsyncResult(HuobiApiException exception) {
    this.exception = exception;
  }

  @Override
  public HuobiApiException getException() {
    return exception;
  }

  @Override
  public boolean succeeded() {
    return false;
  }

  @Override
  public T getData() {
    return null;
  }
}
