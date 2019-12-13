package com.binance.client.examples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.binance.client.SyncRequestClient;
import com.binance.client.examples.constants.Constants;
import com.binance.client.model.CrossMarginAccount;
import com.binance.client.model.CrossMarginLoanOrder;
import com.binance.client.model.enums.CrossMarginTransferType;
import com.binance.client.model.enums.QueryDirection;
import com.binance.client.model.request.CrossMarginApplyLoanRequest;
import com.binance.client.model.request.CrossMarginLoanOrderRequest;
import com.binance.client.model.request.CrossMarginRepayLoanRequest;
import com.binance.client.model.request.CrossMarginTransferRequest;

public class CrossMarginExamples {

  public static void main(String[] args) {
    String currency = "usdt";
    BigDecimal transferAmount = new BigDecimal("50");
    BigDecimal loanAmount = new BigDecimal("100");

    SyncRequestClient syncRequestClient = SyncRequestClient.create(Constants.API_KEY, Constants.SECRET_KEY);

    CrossMarginAccount crossMarginAccount = syncRequestClient.getCrossMarginAccount();

    System.out.println("Cross Margin Account:"+JSON.toJSONString(crossMarginAccount));
    crossMarginAccount.getList().forEach(balance -> {
      System.out.println("Balance:"+JSON.toJSONString(balance));
    });
    System.out.println();


    CrossMarginTransferRequest toMarginRequest = new CrossMarginTransferRequest();
    toMarginRequest.setType(CrossMarginTransferType.SPOT_TO_SUPER_MARGIN);
    toMarginRequest.setCurrency(currency);
    toMarginRequest.setAmount(transferAmount);

    long transferToCrossMargin = syncRequestClient.transferCrossMargin(toMarginRequest);

    System.out.println(" transfer to cross margin :" + transferToCrossMargin);

    CrossMarginApplyLoanRequest loanRequest = new CrossMarginApplyLoanRequest();
    loanRequest.setCurrency(currency);
    loanRequest.setAmount(loanAmount);

    long loanOrderId = syncRequestClient.applyCrossMarginLoan(loanRequest);

    System.out.println(" apply loan order id:" + loanOrderId);


    timeWait(5000);


    CrossMarginRepayLoanRequest repayRequest = new CrossMarginRepayLoanRequest();
    repayRequest.setOrderId(loanOrderId);
    repayRequest.setAmount(loanAmount);

    syncRequestClient.repayCrossMarginLoan(repayRequest);

    System.out.println(" repay loan finish . ");


    timeWait(10000);

    CrossMarginTransferRequest toSpotRequest = new CrossMarginTransferRequest();
    toSpotRequest.setType(CrossMarginTransferType.SUPER_MARGIN_TO_SPOT);
    toSpotRequest.setCurrency(currency);
    toSpotRequest.setAmount(transferAmount);
    long transferToSpot = syncRequestClient.transferCrossMargin(toSpotRequest);

    System.out.println(" transfer to spot :" + transferToSpot);


    List<CrossMarginLoanOrder> orderList = syncRequestClient.getCrossMarginLoanHistory(new CrossMarginLoanOrderRequest());

    orderList.forEach(order->{
      System.out.println("Cross Loan Order:"+new Date(order.getCreatedAt()) +":"+ JSON.toJSONString(order));
    });


  }


  public static void timeWait(long millis) {
    try {
      System.out.println(" time wait " + millis + " ms");
      Thread.sleep(millis);
    } catch (InterruptedException e) {
    }
  }

}
