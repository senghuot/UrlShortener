FROM maven:3.6.0-jdk-8 as MAVEN_BUILD

# Add dependencies and src
COPY pom.xml .
COPY src src/
RUN mvn package

# Run application
FROM openjdk:8
COPY --from=MAVEN_BUILD /target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 9999