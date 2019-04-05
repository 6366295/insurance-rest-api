# Insurance Request REST API

* Designing a REST API for applying for a generic insurance. See [Wiki](https://github.com/6366295/insurance-rest-api/wiki/Design-Document) for design documentation.
* Frameworks: Plain Java
* Data storage solution: SQL
* Build Tool: Gradle

### To do list

* Proper HTTP header processing of incoming requests
* Proper HTTP headers in responses
* CORS support
* Sub-resources support(?), like host/customers/{id}/products to list all insurances a customer applied for.
* Database (Probably Hibernate)
* Client webpage (Using node.js and NPM)

## Installation

```
gradle build
```

## Usage

```
gradle run
```
