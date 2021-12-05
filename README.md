# Library Application

This is an application which allows fetching and adding books into the database.

It consists of several parts:

- API (Spring Boot)
- Worker (Spring Boot)
- Cache (Redis)
- Message Queue (RabbitMQ)
- Database (PostgreSQL)

The project directory contains three modules:

- api - main application entrypoint
- worker - executes saving of the entities
- shared - common classes for both modules

Actions:

- (GET /api/books/{id}) Fetch book by id - API receives a request, checks whether the result is cached and executes DB query if not
- (POST /api/books) Save book - API receives a request, executes RPC on Worker and responds synchronously with created entity id or error message (if a book with given ISBN is already added)

In order to run application, just run in cmd:

`docker-compose up`

In order to test application, you could use postman collection in the root directory.

There is also some seed data (id 1-9) for easier testing. In docker-compose application logs you could see executed DB queries. Thus, you could be convinced that only first book fetch request is being queried, the next ones are being extracted from cache.
