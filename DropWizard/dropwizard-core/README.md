# dropwizard

How to start the dropwizard application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizard-core-2.0.28.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
