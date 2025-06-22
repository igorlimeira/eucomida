# -------- Build Stage --------
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /workspace

COPY . .
#RUN mvn -B -pl auth -am clean package -DskipTests
RUN mvn -B -pl auth -am clean package

# -------- Runtime Stage --------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /workspace/auth/target/eucomida-auth.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar", "--spring.profiles.active=${ACTIVE_PROFILE}"]