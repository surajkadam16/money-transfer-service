package com.suraj.daoimpl;

import com.suraj.dao.CustomerDAO;
import com.suraj.exception.CustomException;
import com.suraj.model.Customer;
import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static Logger log = Logger.getLogger(CustomerDAOImpl.class);
    private final static String SQL_GET_Customer_BY_ID = "SELECT * FROM Customer WHERE CustomerId = ? ";
    private final static String SQL_GET_ALL_CustomerS = "SELECT * FROM Customer";
    private final static String SQL_GET_Customer_BY_NAME = "SELECT * FROM Customer WHERE CustomerName = ? ";
    private final static String SQL_INSERT_Customer = "INSERT INTO Customer (CustomerName, EmailAddress) VALUES (?, ?)";
    private final static String SQL_UPDATE_Customer = "UPDATE Customer SET CustomerName = ?, EmailAddress = ? WHERE CustomerId = ? ";
    private final static String SQL_DELETE_Customer_BY_ID = "DELETE FROM Customer WHERE CustomerId = ? ";

    /**
     * Find all Customers
     */
    public List<Customer> getAllCustomers() throws CustomException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Customer> Customers = new ArrayList<Customer>();
        try {
            conn = H2DAOFactory.getConnection();
            stmt = conn.prepareStatement(SQL_GET_ALL_CustomerS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Customer u = new Customer(rs.getLong("CustomerId"), rs.getString("CustomerName"), rs.getString("EmailAddress"));
                Customers.add(u);
                if (log.isDebugEnabled())
                    log.debug("getAllCustomers() Retrieve Customer: " + u);
            }
            return Customers;
        } catch (SQLException e) {
            throw new CustomException("Error reading Customer data", e);
        } finally {
            DbUtils.closeQuietly(conn, stmt, rs);
        }
    }

    /**
     * Find Customer by CustomerId
     */
    public Customer getCustomerById(long CustomerId) throws CustomException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Customer u = null;
        try {
            conn = H2DAOFactory.getConnection();
            stmt = conn.prepareStatement(SQL_GET_Customer_BY_ID);
            stmt.setLong(1, CustomerId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                u = new Customer(rs.getLong("CustomerId"), rs.getString("CustomerName"), rs.getString("EmailAddress"));
                if (log.isDebugEnabled())
                    log.debug("getCustomerById(): Retrieve Customer: " + u);
            }
            return u;
        } catch (SQLException e) {
            throw new CustomException("Error reading Customer data", e);
        } finally {
            DbUtils.closeQuietly(conn, stmt, rs);
        }
    }

    /**
     * Find Customer by CustomerName
     */
    public Customer getCustomerByName(String CustomerName) throws CustomException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Customer u = null;
        try {
            conn = H2DAOFactory.getConnection();
            stmt = conn.prepareStatement(SQL_GET_Customer_BY_NAME);
            stmt.setString(1, CustomerName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                u = new Customer(rs.getLong("CustomerId"), rs.getString("CustomerName"), rs.getString("EmailAddress"));
                if (log.isDebugEnabled())
                    log.debug("Retrieve Customer: " + u);
            }
            return u;
        } catch (SQLException e) {
            throw new CustomException("Error reading Customer data", e);
        } finally {
            DbUtils.closeQuietly(conn, stmt, rs);
        }
    }

    /**
     * Save Customer
     */
    public long insertCustomer(Customer Customer) throws CustomException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        try {
            conn = H2DAOFactory.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_Customer, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Customer.getCustomerName());
            stmt.setString(2, Customer.getEmailAddress());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                log.error("insertCustomer(): Creating Customer failed, no rows affected." + Customer);
                throw new CustomException("Customers Cannot be created");
            }
            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                log.error("insertCustomer():  Creating Customer failed, no ID obtained." + Customer);
                throw new CustomException("Customers Cannot be created");
            }
        } catch (SQLException e) {
            log.error("Error Inserting Customer :" + Customer);
            throw new CustomException("Error creating Customer data", e);
        } finally {
            DbUtils.closeQuietly(conn,stmt,generatedKeys);
        }

    }

    /**
     * Update Customer
     */
    public int updateCustomer(Long CustomerId,Customer Customer) throws CustomException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = H2DAOFactory.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_Customer);
            stmt.setString(1, Customer.getCustomerName());
            stmt.setString(2, Customer.getEmailAddress());
            stmt.setLong(3, CustomerId);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error Updating Customer :" + Customer);
            throw new CustomException("Error update Customer data", e);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(stmt);
        }
    }

    /**
     * Delete Customer
     */
    public int deleteCustomer(long CustomerId) throws CustomException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = H2DAOFactory.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_Customer_BY_ID);
            stmt.setLong(1, CustomerId);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error Deleting Customer :" + CustomerId);
            throw new CustomException("Error Deleting Customer ID:"+ CustomerId, e);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(stmt);
        }
    }

}
