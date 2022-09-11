FROM openjdk:8
ADD target/customer-rewards.jar customer-rewards.jar
EXPOSE 2020
ENTRYPOINT ["java","-jar","customer-rewards.jar"]