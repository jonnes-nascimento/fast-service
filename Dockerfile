FROM bitnami/git AS clone-stage
WORKDIR /app
RUN git clone --depth 1 https://github.com/jonnes-nascimento/fast-service.git

FROM maven AS build-stage
WORKDIR /app
COPY --from=clone-stage /app/fast-service /app
RUN mvn clean install -DskipTests

FROM openjdk:21
WORKDIR /app
COPY --from=build-stage /app/target/fast-service-1.0.jar /app
EXPOSE 8080
CMD ["java", "-jar", "fast-service-1.0.jar"]