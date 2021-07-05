FROM openjdk:latest
EXPOSE 8080
ADD target/weatherApi.jar weatherApi.jar
ENTRYPOINT ["java","-jar","/weatherApi.jar"]