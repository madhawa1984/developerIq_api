FROM openjdk:17
EXPOSE 8080
ADD target/metrices-api-0.0.1-SNAPSHOT.jar metrices-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","metrices-api-0.0.1-SNAPSHOT.jar"]
