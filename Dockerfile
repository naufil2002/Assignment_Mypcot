# Step 1: Build the app using Maven
FROM maven:3.8.7-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the built app using JAR
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/assignment-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
