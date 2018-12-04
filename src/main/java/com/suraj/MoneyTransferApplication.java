package com.suraj;


import com.suraj.dao.DAOFactory;
import com.suraj.exception.ExceptionMapper;
import com.suraj.resources.AccountResource;
import com.suraj.resources.CustomerResource;
import com.suraj.resources.CustomerTransactionResource;
import com.suraj.utils.PropertyUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Main Class (Starting Point)
 */
public class MoneyTransferApplication {
    private static Logger log = Logger.getLogger(MoneyTransferApplication.class);

    public static void main(String[] args) throws Exception {
        // Initialize H2 database with demo data
        log.info("Initialize demo .....");
        DAOFactory h2DaoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
        h2DaoFactory.populateTestData();
        log.info("Initialisation Complete....");
        // Host service on jetty
        startService();
    }

    private static void startService() throws Exception {
        int port = PropertyUtils.getIntegerProperty("server_port",8080);
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitParameter("jersey.config.server.provider.classnames",
                CustomerResource.class.getCanonicalName()
                        + ","+
                   AccountResource.class.getCanonicalName() + ","
                        + ExceptionMapper.class.getCanonicalName() + ","
                        + CustomerTransactionResource.class.getCanonicalName());
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
}
