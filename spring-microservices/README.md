# Spring Microservices

- Service / Microservice : component of application
	- ex : department and user
- Service Registry : discoverability platform
- API Gateway : for all request to through it
- Hystrix : circuit breaker : NOT WORKING
- Distributed log tracing
- Config Server


### Project Setup
- Look at configuration for all microservices for setup : [config.md](./config.md)


### Making microservice discoverable 

- make the following changes in pom.xml

```xml
    <properties>
        <java.version>17</java.version>
        <spring-cloud.version>2021.0.0</spring-cloud.version>
    </properties>

	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>
```

