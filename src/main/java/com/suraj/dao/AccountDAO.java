package com.suraj.dao;

import com.suraj.exception.CustomException;
import com.suraj.model.Account;
import com.suraj.model.CustomerTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO  {

    List<Account> getAllAccounts() throws CustomException;
    Account getAccountById(long accountId) throws CustomException;
    long createAccount(Account account) throws CustomException;
    int deleteAccountById(long accountId) throws CustomException;

    /**
     *
     * @param accountId user accountId
     * @param deltaAmount amount to be debit(less than 0)/credit(greater than 0).
     * @return no. of rows updated
     */
    int updateAccountBalance(long accountId, BigDecimal deltaAmount) throws CustomException;
    int transferAccountBalance(CustomerTransaction customerTransaction) throws CustomException;
}
