# Etapa de construcción
FROM maven:3.8.1-jdk-11 AS build
COPY pom.xml /app/
COPY src /app/src
WORKDIR /app
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:11-jre-slim
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
