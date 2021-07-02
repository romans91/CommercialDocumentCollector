# CommercialDocumentCollector

This API collects commercial documents such as invoices and credit notes, stores them in a database, and returns a list of these items sorted by their creation date/time.

## Running the application

### Setting up the data source

If you do not have Docker installed on your machine, install Docker Desktop via the [Official Website](https://www.docker.com/get-started).

You can verify that you have Docker installed on your machine using the following command in the command prompt:

`docker version`

With Docker installed, create and run the container that the CommercialDocumentCollector will use as a data source by running the following command in your IDE's terminal:

`docker-compose up`

To connect to the container and access its contents, run the following command via the command prompt:

`docker exec -it db bash`

### Running the Spring Boot application

Run the application via your IDE. 
If you are using IntelliJ IDEA, you can press Shift + F10 to run the application.

After the application runs, note down the port number that shows up in the console:

`Tomcat initialized with port(s): [port number] (http)`

## API

### Post batch of invoices

`POST /api/v1/invoices`

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
        "id": "f30dbe10-5ec0-4c56-9a84-a39af939d9b0",
        "value": 2500.00,
        "invoiceNumber": "INV-0001",
        "createdAt": "1625139465519"
    },
    {
        "id": "8a162b3d-7315-49da-880f-b5b8867448d8",
        "value": 2000.00,
        "invoiceNumber": "INV-0002",
        "createdAt": "1625139465534"
    }
]
```
### Post batch of credit notes

`POST /api/v1/creditnotes`

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
        "id": "825a6c1a-90ec-4e57-a55f-a93a585364de",
        "value": 1200.00,
        "creditNumber": "CRD-0001",
        "createdAt": "1625139469152"
    },
    {
        "id": "5bb36ea2-8ed8-4428-b7c9-0110173a7808",
        "value": 1500.00,
        "creditNumber": "CRD-0002",
        "createdAt": "1625139469158"
    }
]
```

### Get a page of commercial documents sorted by creation date/time

`GET /api/v1/commercialdocuments?page=[integer]&size=[integer]`

### URL Params

Page: Integer

Example:``page=[0]``

Size: Integer

Example:``size=[0]``

Response body:
```
{
    "content": [
        {
            "id": "825a6c1a-90ec-4e57-a55f-a93a585364de",
            "value": 1200.00,
            "creditNumber": "CRD-0001",
            "createdAt": "1625139469152"
        },
        {
            "id": "5bb36ea2-8ed8-4428-b7c9-0110173a7808",
            "value": 1500.00,
            "creditNumber": "CRD-0002",
            "createdAt": "1625139469158"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 2,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalPages": 2,
    "totalElements": 4,
    "size": 2,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```

### Get all commercial documents sorted by creation date/time

`GET /api/v1/commercialdocuments`

Response body:
```
[
    {
        "id": "f30dbe10-5ec0-4c56-9a84-a39af939d9b0",
        "value": 2500.00,
        "invoiceNumber": "INV-0001",
        "createdAt": "1625139465519"
    },
    {
        "id": "8a162b3d-7315-49da-880f-b5b8867448d8",
        "value": 2000.00,
        "invoiceNumber": "INV-0002",
        "createdAt": "1625139465534"
    },
    {
        "id": "825a6c1a-90ec-4e57-a55f-a93a585364de",
        "value": 1200.00,
        "creditNumber": "CRD-0001",
        "createdAt": "1625139469152"
    },
    {
        "id": "5bb36ea2-8ed8-4428-b7c9-0110173a7808",
        "value": 1500.00,
        "creditNumber": "CRD-0002",
        "createdAt": "1625139469158"
    }
]
```