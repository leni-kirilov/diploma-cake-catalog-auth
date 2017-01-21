# Cake catalog - authentication service

## Prerequisites

Java 8 (7 for client)
Gradle
Mysql

```CREATE DATABASE cake_catalog_auth

CREATE TABLE portal_user
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  version BIGINT(20) NOT NULL,
  email VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);
```

## How to run server
Default port 8008 for development

```
./run-local.sh
```

## How to run server with production DB
```
./run-local-postgre.sh
```