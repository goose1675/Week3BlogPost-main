# Use a Java 21 runtime image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy all project files
COPY . /app

# Build the Spring Boot app (includes frontend in static)
RUN ./mvnw clean package

# Expose port 8080
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/blogpost-0.0.1-SNAPSHOT.jar"]