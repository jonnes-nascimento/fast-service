FROM openjdk:21
WORKDIR /app
COPY ./target/fast-service-1.0.jar /app
EXPOSE 8080
CMD ["java", "-jar", "fast-service-1.0.jar"]