# Start with a base image containing Java runtime
FROM openjdk:11-jre-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8081 available to the world outside this container
EXPOSE 8081

# The application's jar file
ARG JAR_FILE=target/myapp.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-Dspring.config.location=classpath:/application.yml","-jar","/app.jar"]