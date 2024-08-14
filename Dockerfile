FROM bitnami/git AS CLONE-STAGE
WORKDIR /app
RUN git clone --depth 1 https://github.com/jonnes-nascimento/fast-service.git

FROM maven AS BUILD-STAGE
WORKDIR /app
COPY --from=CLONE-STAGE /app/fast-service /app
RUN mvn clean install -DskipTests

FROM openjdk:21
WORKDIR /app
COPY --from=BUILD-STAGE /app/target/fast-service-1.0.jar /app
EXPOSE 8080
CMD ["java", "-jar", "fast-service-1.0.jar"]