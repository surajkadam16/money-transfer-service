package com.suraj.resources;

import com.suraj.dao.DAOFactory;
import com.suraj.exception.CustomException;
import com.suraj.model.Customer;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private final DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);

    private static Logger log = Logger.getLogger(CustomerResource.class);

    /**
     * Find by CustomerName
     * @param CustomerName
     * @return
     * @throws CustomException
     */
    @GET
    @Path("/{CustomerName}")
    public Customer getCustomerByName(@PathParam("CustomerName") String CustomerName) throws CustomException {
        if (log.isDebugEnabled())
            log.debug("Request Received for get Customer by Name " + CustomerName);
        final Customer Customer = daoFactory.getCustomerDAO().getCustomerByName(CustomerName);
        if (Customer == null) {
            throw new WebApplicationException("Customer Not Found", Response.Status.NOT_FOUND);
        }
        return Customer;
    }

    /**
     * Find by all
     * @param CustomerName
     * @return
     * @throws CustomException
     */
    @GET
    @Path("/all")
    public List<Customer> getAllCustomers() throws CustomException {
        return daoFactory.getCustomerDAO().getAllCustomers();
    }

    /**
     * Create Customer
     * @param Customer
     * @return
     * @throws CustomException
     */
    @POST
    @Path("/create")
    public Customer createCustomer(Customer Customer) throws CustomException {
        if (daoFactory.getCustomerDAO().getCustomerByName(Customer.getCustomerName()) != null) {
            throw new WebApplicationException("Customer name already exist", Response.Status.BAD_REQUEST);
        }
        final long uId = daoFactory.getCustomerDAO().insertCustomer(Customer);
        return daoFactory.getCustomerDAO().getCustomerById(uId);
    }

    /**
     * Find by Customer Id
     * @param CustomerId
     * @param Customer
     * @return
     * @throws CustomException
     */
    @PUT
    @Path("/{CustomerId}")
    public Response updateCustomer(@PathParam("CustomerId") long CustomerId, Customer Customer) throws CustomException {
        final int updateCount = daoFactory.getCustomerDAO().updateCustomer(CustomerId, Customer);
        if (updateCount == 1) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Delete by Customer Id
     * @param CustomerId
     * @return
     * @throws CustomException
     */
    @DELETE
    @Path("/{CustomerId}")
    public Response deleteCustomer(@PathParam("CustomerId") long CustomerId) throws CustomException {
        int deleteCount = daoFactory.getCustomerDAO().deleteCustomer(CustomerId);
        if (deleteCount == 1) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
