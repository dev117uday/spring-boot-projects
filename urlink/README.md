# Project URLink

_Collect and share URLs_

### Project Setup : Configurations

#### Localhost 

- Configure postgres

```sql
CREATE ROLE urlinkapp WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'xxxxxx';  

CREATE DATABASE urlinkdb
    WITH 
    OWNER = urlinkapp
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
```

- Start Eureka Server : https://github.com/dev117uday/eureka-service-registry-server

```sh
mkdir src/main/resources

touch src/main/resources/application.yml
```

- Create a file `application.yml`

```yml
spring:
  datasource:
    username: urlinkapp
    url: jdbc:postgresql://localhost:5432/urlinkdb
    password: Password02117
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: 'true'
  application:
    name: URLINK-APP
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka
```