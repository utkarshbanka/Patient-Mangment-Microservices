# ---------- STAGE 1: Build ----------
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy only pom first (better caching)
COPY pom.xml .

# Download dependencies
RUN mvn -B dependency:go-offline

# Copy source code
COPY src ./src

# Build jar
RUN mvn -B clean package -DskipTests


# ---------- STAGE 2: Run ----------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy the built jar (use wildcard to avoid name mismatch issues)
COPY --from=builder /app/target/*.jar app.jar

# Expose only the port your app actually uses
EXPOSE 4001
EXPOSE 9001

# Run application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]