package com.suraj.dao;

import com.suraj.daoimpl.H2DAOFactory;

public abstract class DAOFactory {
    public static final int H2 = 1;

    public abstract CustomerDAO getCustomerDAO();

    public abstract AccountDAO getAccountDAO();

    public abstract void populateTestData();

    public static DAOFactory getDAOFactory(int factoryCode) {

        switch (factoryCode) {
            case H2:
                return new H2DAOFactory();
            default:
                // by default using H2 in memory database
                return new H2DAOFactory();
        }
    }
}
