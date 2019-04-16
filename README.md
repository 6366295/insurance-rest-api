# Insurance Request REST API

Designing a REST API for applying for a generic insurance.

* Frameworks: Spring Framework
* Database Framework: Hibernate JPA2
* Database: PostgreSQL
* Build Tool: Gradle

### To do list

* Use Spring Data
* Separate database from controller
* Error Handling
* (Unit) Testing
* Make Docker Image

## Usage

```
gradle run
```

## Setup PostgreSQL using Docker

Pull the latest PostgreSQL docker image:

```
docker pull postgres
```

Run PostgreSQL using Docker, auto creates database :

```
docker run --rm -d \
--name <container name> \
--mount type=bind,source=<volume path>,destination=/var/lib/postgresql/data \
-e POSTGRES_PASSWORD=<password> \
-e POSTGRES_USER=<user> \
-e POSTGRES_DB=<database name> \
-p <port>:5432 \
postgres
```

Stop docker running PostgreSQL database:

```
docker stop <container name>
```

Enter PostgreSQL command-line:

```
psql -h localhost -p <port> -U <user> -d <database name>
```
