package com.suraj.dao;

import com.suraj.exception.CustomException;
import com.suraj.model.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getAllCustomers() throws CustomException;

    Customer getCustomerById(long CustomerId) throws CustomException;

    Customer getCustomerByName(String CustomerName) throws CustomException;

    /**
     * @param Customer:
     * Customer to be created
     * @return CustomerId generated from insertion. return -1 on error
     */
    long insertCustomer(Customer Customer) throws CustomException;

    int updateCustomer(Long CustomerId, Customer Customer) throws CustomException;

    int deleteCustomer(long CustomerId) throws CustomException;

}
