# Start with a base image containing Java runtime
FROM openjdk:8u181-slim

# Add Maintainer Info
MAINTAINER Ulisses Nairne de Almeida <ulissesnairne@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's properties
COPY ./src/main/resources/properties/log4j.properties /oracle/gerenciador-energia/
COPY ./src/main/resources/properties/log-gerenciador-energia.properties /oracle/gerenciador-energia/

# The application's jar file
COPY ./target/gerenciador-energia.jar /usr/src/gerenciador-energia/
WORKDIR /usr/src/gerenciador-energia/

# Run the jar file
CMD ["java", "-jar", "gerenciador-energia.jar"]
