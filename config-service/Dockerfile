FROM openjdk:17-alpine
COPY ./build/libs/config-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch config-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/config-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /config-service-0.0.1-SNAPSHOT.jar