package com.suraj.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    @JsonIgnore
    private long accountId;
    private String customerName;
    private BigDecimal balance;
    private String currencyCode;

    public Account() {
    }

    public Account(String customerName, BigDecimal balance, String currencyCode) {
        this.customerName = customerName;
        this.balance = balance;
        this.currencyCode = currencyCode;
    }

    public Account(long accountId, String customerName, BigDecimal balance, String currencyCode) {
        this.accountId = accountId;
        this.customerName = customerName;
        this.balance = balance;
        this.currencyCode = currencyCode;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId &&
                Objects.equals(customerName, account.customerName) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(currencyCode, account.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, customerName, balance, currencyCode);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customerName='" + customerName + '\'' +
                ", balance=" + balance +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
