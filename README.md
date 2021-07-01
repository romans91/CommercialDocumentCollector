# CommercialDocumentCollector

This API collects commercial documents such as invoices and credit notes, stores them in a database, and returns a list of these items sorted by their creation date/time.

## Running the application

### MySql setup

If you do not have MySql set up on your machine, download the installer from the [MySql Website](https://dev.mysql.com/downloads/mysql/). 
Select the custom installation option, and then install "MySql Server" and "MySql Workbench". 

### MySql data source

When you run the MySql Workbench, you should have a connection in the "MySql Connections" section of the workbench with a localhost IP and a port number. 
If you do not see a connection, you will be able to create one by clicking on the "+" button.

Double-click on the connection you wish to use for this application to connect to it and get access to the databases. 
Create a database to be used by the CommercialDocumentCollector application using the following command:

`create database [database name]`

This application will automatically create and use two tables named "credit_notes" and "invoices".

### Connecting your application to the database

Open your IDE, and use it to open the base folder of this application.
Open "application.properties" via "src" -> "main" -> "resources".
Set the values of the following three properties to the values you have configured in MySql:

```
spring.datasource.url = jdbc:mysql://localhost:[port number]/[database name]
spring.datasource.username = [root username]
spring.datasource.password = [root password]
```

### Running the application

Run the application via your IDE. 
If you are using IntelliJ IDEA, you can press Shift + F10 to run the application.

After the application runs, note down the port number that shows up in the console:

`Tomcat initialized with port(s): [port number] (http)`

## API

### Post batch of invoices

`POST http://localhost:8080/api/v1/invoices`

Request body:
```
[
    {
        "invoiceNumber": "INV-0001",
        "value" :"2500.00" 
    }, 
    {
        "invoiceNumber": "INV-0002",
        "value" :"2000.00" 
    }
]
```
Response body:
```
[
    {
        "id": "6e2b2142-5d80-428c-8ab0-21b92aa9520e",
        "value": 2500.00,
        "invoiceNumber": "INV-0001",
        "createdAt": "1625105067847"
    },
    {
        "id": "31e0e8e5-ca2c-488c-ad60-f6515f90bde6",
        "value": 2000.00,
        "invoiceNumber": "INV-0002",
        "createdAt": "1625105067863"
    }
]
```
### Post batch of credit notes

`POST http://localhost:8080/api/v1/creditnotes`

Request body:
```
[
    {
        "creditNumber": "CRD-0001",
        "value" :"1200.00" 
    }, 
    {
        "creditNumber": "CRD-0002",
        "value" :"1500.00" 
    }
]
```

Response body:
```
[
    {
        "id": "289bcbf7-a873-494d-b608-45c1eef4c0fc",
        "value": 1200.00,
        "creditNumber": "CRD-0001",
        "createdAt": "1625105070842"
    },
    {
        "id": "870a838a-6a67-4bcc-958e-152674326a5d",
        "value": 1500.00,
        "creditNumber": "CRD-0002",
        "createdAt": "1625105070844"
    }
]
```
### Get all commercial documents sorted by creation date/time

`GET http://localhost:8080/api/v1/commercialdocuments`

Response body:
```
[
    {
        "id": "6e2b2142-5d80-428c-8ab0-21b92aa9520e",
        "value": 2500.00,
        "invoiceNumber": "INV-0001",
        "createdAt": "1625105068000"
    },
    {
        "id": "31e0e8e5-ca2c-488c-ad60-f6515f90bde6",
        "value": 2000.00,
        "invoiceNumber": "INV-0002",
        "createdAt": "1625105068000"
    },
    {
        "id": "289bcbf7-a873-494d-b608-45c1eef4c0fc",
        "value": 1200.00,
        "creditNumber": "CRD-0001",
        "createdAt": "1625105071000"
    },
    {
        "id": "870a838a-6a67-4bcc-958e-152674326a5d",
        "value": 1500.00,
        "creditNumber": "CRD-0002",
        "createdAt": "1625105071000"
    }
]
```