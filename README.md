# Insurance Request REST API

Designing a REST API for applying for a generic insurance.

* Frameworks: Spring Framework
* Database Framework: Hibernate JPA2 / Spring Data
* Database: PostgreSQL
* Build Tool: Gradle / Docker

### To do list

* Error Handling
* (Unit) Testing

## New Usage

Build:

```
docker-compose build
```

Run:

```
docker-compose up
```

## Setup PostgreSQL using Docker (Old)

Pull the latest PostgreSQL docker image:

```
docker pull postgres
```

Create new Docker bridge network

```
docker network create -d bridge <docker network name>
```

Run PostgreSQL using Docker, auto creates database :

```
docker run --rm -d \
--name <container name> \
--mount type=bind,source=<volume path>,destination=/var/lib/postgresql/data \
--network <docker network name>
-e POSTGRES_PASSWORD=<password> \
-e POSTGRES_USER=<user> \
-e POSTGRES_DB=<database name> \
-p <publish port>:5432 \
postgres
```

Stop docker running PostgreSQL database:

```
docker stop <container name>
```

Enter PostgreSQL command-line:

```
psql -h localhost -p <publish port> -U <user> -d <database name>
```

## Usage (Old)

Change persistence.xml property:

```
<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://<container name>:5432/<database name>"/>
```

Build:

```
gradle build && docker build --tag=<docker image name> .
```

Run:

```
docker run --rm \
--name <container name 2> \
--network <docker network name> \
-p 8080:8080 \
<docker image name>
```

Stop:

```
docker stop --rm <container name 2>
```
