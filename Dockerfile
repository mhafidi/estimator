FROM openjdk:8-jdk-alpine
COPY target/estimator-1.0.0.jar estimator.jar
ENTRYPOINT ["java","-jar","/estimator.jar"]