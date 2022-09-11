## Customer Reward Points Springboard API

## Description
This Springboot APIs are used to get the last three months customer transaction reward points.

## Technology

- **Spring Boot**     - Server side framework
- **JPA**             - Entity framework
- **Actuator**        - Application insights on the fly, health monitor
- **Security**        - Spring Basic security
- **Swagger**         - In-built swagger documentation support
- **Docker**          - Docker containers
- **Junit**           - Unit testing framework
- **Gliffy**          - ER diagrams design tool
- **PlanUML**         - Draw sequence diagrams

## Application Structure
![Project](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/project-structure.png)

## Database Structure
![DB](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/h2-db-console.png)

## Entity Relation Model
![E-R](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/er-diagram.png)

## Sequence Diagram
![sequence](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/sequence-diagram.png)

## Running the server locally
The pom.xml can be configured to generate a executable jar file.

````
java -jar customer-rewards.jar
OR
mvn spring-boot:run

````

## API Security
Basic authentication will pop-up to enter the username and password (rewardapi/rewardapi)
![Security](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/secure-api.png)


## Service Actuator Endpoints
Following URL is used to get the service Actuator Endpoints.

````
http://<host-name>:2020/actuator

````

![Actuator](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/actuator-endpoints.png)


## Service Health Monitor With Actuator
Following URL is used to get the service health. Another way could be enabled the prometheus metrics configuration and export data to grafana tool and design dashboards.

````
http://<host-name>:2020/actuator/health

````
![Health](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/health-monitor.png)


## Functional Test Results

1. Customer with card number *PAN1234* last 3 months reward points.
![PAN1234](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/PAN1234-reward-points.png)

2. Customer with card number *PAS1234* last 3 months reward points.
![PAS1234](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/PAS1234-reward-points.png)

3. Create New customer record.
![New Customer](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/add-new-customer.png)

4. Crate Customer which already exists (card number should unique).
![New Customer Fail](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/add-fail-cutomer-exists.png)

5. Add New transaction of newly created customer.
![New Trans](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/add-new-transaction.png)

6. Customer with card number *PAS1234* last 3 months reward points.
![ADHAR123](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/ADHAR123-reward-points.png)

7. Delete Customer, which will delete associated transations as well.
![Delete Customer](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/delete-customer.png)

## Junit Results
![jUnit](https://github.com/YugandharGali/cutomer-rewards/blob/main/src/main/resources/static/img/junit-results.png)


## Docker

* Create docker image from this project -

````
# 1 Login Docker -> docker login : yugandhargali / Y.....
# 2 Build Docker image -> docker build -f Dockerfile -t customer-rewards .
# 3 See Docker image -> docker images
# 4 Run & Push Docker image -> docker run -p 2020:2020 customer-rewards / docker run customer-rewards / docker stop customer-rewards
# 5 Access App -> http://<host-name>:2020/swagger-ui.html

#docker stop customer-rewards

````

## Swagger Documentation
Swagger documentation is in-built swagger documentation support and can be accessed at the following URL -

````
http://<host-name>:2020/swagger-ui.html
````

## Contributors
[Yugandhar Gali](https://www.linkedin.com/in/yugandhar-gali-84591050/)


