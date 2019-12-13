package com.binance.client.examples;

import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.binance.client.SyncRequestClient;
import com.binance.client.examples.constants.Constants;
import com.binance.client.model.Account;
import com.binance.client.model.AccountHistory;
import com.binance.client.model.Deposit;
import com.binance.client.model.DepositAddress;
import com.binance.client.model.Withdraw;
import com.binance.client.model.WithdrawQuota;
import com.binance.client.model.enums.AccountType;
import com.binance.client.model.request.AccountHistoryRequest;
import com.binance.client.model.request.WithdrawRequest;


public class WalletExamples {

  public static void main(String[] args) {
    String currency = "usdt";

    SyncRequestClient syncRequestClient = SyncRequestClient.create(Constants.API_KEY, Constants.SECRET_KEY);


    Long withdrawId = syncRequestClient.withdraw(new WithdrawRequest("address",new BigDecimal(10),currency,"usdterc20"));
    System.out.println(withdrawId);

    syncRequestClient.cancelWithdraw(currency,withdrawId);

    List<Account> accountList = syncRequestClient.getAccountBalance();
    accountList.forEach(account -> {
      System.out.println(JSON.toJSONString(account));
    });

    Account spotAccount = syncRequestClient.getAccountBalance(AccountType.SPOT);
    System.out.println(JSON.toJSONString(spotAccount));

    Account marginAccount = syncRequestClient.getAccountBalance(AccountType.MARGIN, "xrpusdt");
    System.out.println(JSON.toJSONString(marginAccount));

    Account superMarginAccount = syncRequestClient.getAccountBalance(AccountType.SUPER_MARGIN);
    System.out.println(JSON.toJSONString(superMarginAccount));

    List<DepositAddress> addressList = syncRequestClient.getDepositAddress(currency);
    addressList.forEach(address -> {
      System.out.println("Deposit Address:" + JSON.toJSONString(address));
    });


    WithdrawQuota withdrawQuota = syncRequestClient.getWithdrawQuota(currency);
    System.out.println("==============" + withdrawQuota.getCurrency() + "===============");
    withdrawQuota.getChains().forEach(chainQuota -> {
      System.out.println("Chain Quota :" + JSON.toJSONString(chainQuota));
    });


    AccountHistoryRequest accountHistoryRequest = new AccountHistoryRequest();
    accountHistoryRequest.setAccountId(290082L);
    List<AccountHistory> accountHistoryList = syncRequestClient.getAccountHistory(accountHistoryRequest);

    accountHistoryList.forEach(accountHistory -> {
      System.out.println("Account History:"+JSON.toJSONString(accountHistory));
    });


    /**
     * Get withdraw history
     */
    List<Withdraw> withdrawList = syncRequestClient.getWithdrawHistory(currency, 0, 10);
    withdrawList.forEach(withdraw -> {
      System.out.println("Withdraw History:" + JSON.toJSONString(withdraw));
    });

    /**
     * Get Deposit history
     */
    List<Deposit> depositList = syncRequestClient.getDepositHistory(currency,0,10);
    depositList.forEach(deposit -> {
      System.out.println("Deposit History:" + JSON.toJSONString(deposit));
    });
  }

}
