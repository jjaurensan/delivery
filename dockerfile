#### Stage 1: Build the application
FROM openjdk:12-jdk-alpine

# Add Maintener info
LABEL maintainer="jj.aurensan@gmail.com"

# Add a volume pointing to /tmp
VOLUME [ "/tmp" ]

#Make port8181 available to the world outside this container
EXPOSE 8080

# the application's jar file
ARG JAR_FILE=target/Delivery-0.0.1-SNAPSHOT.jar

# Add the application jar to the container
ADD ${JAR_FILE} Delivery.jar


ENTRYPOINT ["java","-jar","/Delivery.jar"]