package com.suraj.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Customer {

    @JsonIgnore
    private long customerId ;

    private String customerName;

    private String emailAddress;

    public Customer() {
    }

    public Customer(String customerName, String emailAddress) {
        this.customerName = customerName;
        this.emailAddress = emailAddress;
    }

    public Customer(long customerId, String customerName, String emailAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.emailAddress = emailAddress;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(emailAddress, customer.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, emailAddress);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
