# Use a Maven image as the build stage
FROM maven:3.8.4-openjdk-11 AS builder

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src src

# Download the dependencies and build the project
RUN mvn dependency:go-offline -B
RUN mvn package -DskipTests

# Use a smaller base image for the runtime
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/your-spring-boot-app.jar .

# Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "your-spring-boot-app.jar"]
