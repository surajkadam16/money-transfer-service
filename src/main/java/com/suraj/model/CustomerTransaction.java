package com.suraj.model;

import java.math.BigDecimal;
import java.util.Objects;

public class CustomerTransaction {
    private String currencyCode;
    private BigDecimal amount;
    private Long fromAccountId;
    private Long toAccountId;

    public CustomerTransaction() {
    }

    public CustomerTransaction(String currencyCode, BigDecimal amount, Long fromAccountId, Long toAccountId) {
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerTransaction that = (CustomerTransaction) o;
        return Objects.equals(currencyCode, that.currencyCode) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(fromAccountId, that.fromAccountId) &&
                Objects.equals(toAccountId, that.toAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode, amount, fromAccountId, toAccountId);
    }

    @Override
    public String toString() {
        return "CustomerTransaction{" +
                "currencyCode='" + currencyCode + '\'' +
                ", amount=" + amount +
                ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                '}';
    }
}
