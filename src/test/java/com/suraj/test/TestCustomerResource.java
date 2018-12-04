package com.suraj.test;

import com.suraj.model.Customer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;


/**
 * Integration testing for RestAPI
 * Test data are initialised from src/test/resources/demo.sql
 * INSERT INTO Customer (CustomerName, EmailAddress) VALUES ('test2','test2@gmail.com');  --ID=1
   INSERT INTO Customer (CustomerName, EmailAddress) VALUES ('test1','test1@gmail.com');  --ID=2
 */
public class TestCustomerResource extends TestService {

    /*
       TC D1 Positive Category = CustomerService
       Scenario: test get Customer by given Customer name
                 return 200 OK
    */
    @Test
    public void testGetCustomer() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/customer/suraj").build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        assertTrue(statusCode == 200);
        //check the content
        String jsonString = EntityUtils.toString(response.getEntity());
        Customer Customer = mapper.readValue(jsonString, Customer.class);
        assertTrue(Customer.getCustomerName().equals("suraj"));
        assertTrue(Customer.getEmailAddress().equals("suraj_test1@gmail.com"));

    }

     /*
     TC D2 Positive Category = CustomerService
     Scenario: test get all Customers
               return 200 OK
      */
    @Test
    public void testGetAllCustomers() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/customer/all").build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue(statusCode == 200);
        //check the content
        String jsonString = EntityUtils.toString(response.getEntity());
        Customer[] Customers = mapper.readValue(jsonString, Customer[].class);
        assertTrue(Customers.length > 0);
    }

    /*
        TC D3 Positive Category = CustomerService
        Scenario: Create Customer using JSON
                  return 200 OK
     */
    @Test
    public void testCreateCustomer() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/customer/create").build();
        Customer Customer = new Customer("liandre", "liandre@gmail.com");
        String jsonInString = mapper.writeValueAsString(Customer);
        StringEntity entity = new StringEntity(jsonInString);
        HttpPost request = new HttpPost(uri);
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue(statusCode == 200);
        String jsonString = EntityUtils.toString(response.getEntity());
        Customer uAfterCreation = mapper.readValue(jsonString, Customer.class);
        assertTrue(uAfterCreation.getCustomerName().equals("liandre"));
        assertTrue(uAfterCreation.getEmailAddress().equals("liandre@gmail.com"));
    }

    /*
        TC D4 Negative Category = CustomerService
        Scenario: Create Customer already existed using JSON
                  return 400 BAD REQUEST
    */
    @Test
    public void testCreateExistingCustomer() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/customer/create").build();
        Customer Customer = new Customer("suraj", "suraj_test1@gmail.com");
        String jsonInString = mapper.writeValueAsString(Customer);
        StringEntity entity = new StringEntity(jsonInString);
        HttpPost request = new HttpPost(uri);
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue(statusCode == 400);

    }

    /*
     TC D5 Positive Category = CustomerService
     Scenario: Update Existing Customer using JSON provided from client
               return 200 OK
     */
    @Test
    public void testUpdateCustomer() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/customer/1").build();
        Customer Customer = new Customer(1L, "suraj", "suraj_test1@gmail.com");
        String jsonInString = mapper.writeValueAsString(Customer);
        StringEntity entity = new StringEntity(jsonInString);
        HttpPut request = new HttpPut(uri);
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue(statusCode == 200);
    }


    /*
    TC D6 Negative Category = CustomerService
    Scenario: Update non existed Customer using JSON provided from client
              return 404 NOT FOUND
    */
    @Test
    public void testUpdateNonExistingCustomer() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/Customer/100").build();
        Customer Customer = new Customer(2L, "test1", "test1123@gmail.com");
        String jsonInString = mapper.writeValueAsString(Customer);
        StringEntity entity = new StringEntity(jsonInString);
        HttpPut request = new HttpPut(uri);
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue(statusCode == 404);
    }

    /*
     TC D7 Positive Category = CustomerService
     Scenario: test delete Customer
                return 200 OK
    */
    @Test
    public void testDeleteCustomer() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/customer/3").build();
        HttpDelete request = new HttpDelete(uri);
        request.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue(statusCode == 200);
    }


    /*
    TC D8 Negative Category = CustomerService
    Scenario: test delete non-existed Customer
              return 404 NOT FOUND
   */
    @Test
    public void testDeleteNonExistingCustomer() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/Customer/300").build();
        HttpDelete request = new HttpDelete(uri);
        request.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue(statusCode == 404);
    }


}
