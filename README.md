# Money transfer Rest API

A Java RESTful API for money transfers between customer accounts

## Technologies

JAX-RS API

H2 in memory database

Log4j

Jetty Container (for Test and Demo app)

Apache HTTP Client

## How to run

mvn exec:java

## Application starts a jetty server on localhost port 8090 An H2 in memory database initialized with some sample user and account data To view

http://localhost:8090/customer/suraj

http://localhost:8090/customer/nakul

http://localhost:8090/account/1

http://localhost:8090/account/2

## Available Services

| HTTP METHOD |	PATH | USAGE |
| ---------- |:-------------:| -----:|
| GET	| /customer/{customerName}	| get customer by customer name |
|GET	| /customer/all	| get all customers
|PUT	| /customer/create |	create a new customer
|POST	| /customer/{customerId} |	update customer
|DELETE	| /customer/{customerId} |	remove customer
|GET	| /account/{accountId}	| get account by accountId
|GET	| /account/all	| get all accounts
|GET	| /account/{accountId}/balance |	get account balance by accountId
|PUT	| /account/create	| create a new account
|DELETE	| /account/{accountId}	| remove account by accountId
|PUT	| /account/{accountId}/withdraw/{amount} |	withdraw money from account
|PUT	| /account/{accountId}/deposit/{amount}	| deposit money to account
|POST	| /transaction |	perform transaction between 2 customer accounts

## Http Status

- 200 OK: The request has succeeded

- 400 Bad Request: The request could not be understood by the server

- 404 Not Found: The requested resource cannot be found

- 500 Internal Server Error: The server encountered an unexpected condition

## Sample JSON for User and Account

### Customer :
{  
  "customerName":"suraj",
  
  "emailAddress":"suraj_test1@gmail.com"
  
} 

### Customer Account: :
{  
   "customerName":"suraj",
   
   "balance":100.0000,
   
   "currencyCode":"USD"
   
} 

### Customer Transaction:
{  
   "currencyCode":"USD",
   
   "amount":10.0000,
   
   "fromAccountId":1,
   
   "toAccountId":2
   
}
