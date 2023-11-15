FROM openjdk:17-alpine
COPY ./build/libs/client-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch client-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/client-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /client-service-0.0.1-SNAPSHOT.jar