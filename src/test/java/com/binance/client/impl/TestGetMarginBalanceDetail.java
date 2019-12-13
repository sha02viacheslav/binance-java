package com.binance.client.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.binance.client.RequestOptions;
import com.binance.client.exception.HuobiApiException;
import com.binance.client.impl.utils.JsonWrapper;
import com.binance.client.model.Balance;
import com.binance.client.model.MarginBalanceDetail;
import com.binance.client.model.enums.AccountState;
import com.binance.client.model.enums.AccountType;
import com.binance.client.model.enums.BalanceType;
import com.binance.client.model.request.LoanOrderRequest;
import java.math.BigDecimal;
import java.util.List;
import okhttp3.Request;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest({AccountsInfoMap.class})
@PowerMockIgnore({"javax.crypto.*"})
public class TestGetMarginBalanceDetail {

  private RestApiRequestImpl impl = new RestApiRequestImpl("12345", "67890", new RequestOptions());

  private String data = "{\n"
      + "\t\"status\": \"ok\",\n"
      + "\t\"data\": [{\n"
      + "\t\t\"id\": 6725534,\n"
      + "\t\t\"type\": \"margin\",\n"
      + "\t\t\"state\": \"working\",\n"
      + "\t\t\"symbol\": \"btcusdt\",\n"
      + "\t\t\"fl-price\": \"0\",\n"
      + "\t\t\"fl-type\": \"safe\",\n"
      + "\t\t\"risk-rate\": \"10\",\n"
      + "\t\t\"list\": [{\n"
      + "\t\t\t\"currency\": \"btc\",\n"
      + "\t\t\t\"type\": \"trade\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"btc\",\n"
      + "\t\t\t\"type\": \"frozen\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"btc\",\n"
      + "\t\t\t\"type\": \"loan\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"btc\",\n"
      + "\t\t\t\"type\": \"interest\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"usdt\",\n"
      + "\t\t\t\"type\": \"trade\",\n"
      + "\t\t\t\"balance\": \"3\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"usdt\",\n"
      + "\t\t\t\"type\": \"frozen\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"usdt\",\n"
      + "\t\t\t\"type\": \"loan\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"usdt\",\n"
      + "\t\t\t\"type\": \"interest\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"btc\",\n"
      + "\t\t\t\"type\": \"transfer-out-available\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"usdt\",\n"
      + "\t\t\t\"type\": \"transfer-out-available\",\n"
      + "\t\t\t\"balance\": \"3\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"btc\",\n"
      + "\t\t\t\"type\": \"loan-available\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}, {\n"
      + "\t\t\t\"currency\": \"usdt\",\n"
      + "\t\t\t\"type\": \"loan-available\",\n"
      + "\t\t\t\"balance\": \"0\"\n"
      + "\t\t}]\n"
      + "\t}]\n"
      + "}";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testRequest() {
    RestApiRequest<List<MarginBalanceDetail>> restApiRequest = impl.getMarginBalanceDetail("btcusdt");
    Request request = restApiRequest.request;
    assertEquals("GET", request.method());
    assertTrue(request.url().toString().contains("/v1/margin/accounts/balance"));
    assertEquals("btcusdt", request.url().queryParameter("symbol"));
  }

  @Test
  public void testResult() {
    RestApiRequest<List<MarginBalanceDetail>> restApiRequest = impl.getMarginBalanceDetail("btcusdt");
    List<MarginBalanceDetail> marginBalanceDetailList =
        restApiRequest.jsonParser.parseJson(JsonWrapper.parseFromString(data));
    assertEquals(1, marginBalanceDetailList.size());
    assertEquals(12, marginBalanceDetailList.get(0).getSubAccountBalance().size());
    assertEquals(6725534, marginBalanceDetailList.get(0).getId());
    assertEquals(AccountType.MARGIN, marginBalanceDetailList.get(0).getType());
    assertEquals(AccountState.WORKING, marginBalanceDetailList.get(0).getState());
    assertEquals("btcusdt", marginBalanceDetailList.get(0).getSymbol());
    assertEquals(new BigDecimal("0"), marginBalanceDetailList.get(0).getFlPrice());
    assertEquals("safe", marginBalanceDetailList.get(0).getFlType());
    assertEquals(new BigDecimal("10"), marginBalanceDetailList.get(0).getRiskRate());
    assertEquals("usdt", marginBalanceDetailList.get(0).getSubAccountBalance().get(4).getCurrency());
    assertEquals(BalanceType.TRADE, marginBalanceDetailList.get(0).getSubAccountBalance().get(4).getType());
    assertEquals(new BigDecimal("3"), marginBalanceDetailList.get(0).getSubAccountBalance().get(4).getBalance());
  }

  @Test
  public void testInvalidSymbol() {
    thrown.expect(HuobiApiException.class);
    impl.getMarginBalanceDetail("***");
  }
}
