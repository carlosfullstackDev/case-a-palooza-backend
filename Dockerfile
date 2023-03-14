FROM openjdk:19-jdk-alpine
MAINTAINER casePalooza
COPY target/customers-0.0.1-SNAPSHOT.jar customers-1.0.0.jar
ENTRYPOINT ["java","-jar","/customers-1.0.0.jar"]