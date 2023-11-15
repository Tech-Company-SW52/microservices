FROM openjdk:17-alpine
COPY ./build/libs/carrier-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch carrier-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/carrier-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /carrier-service-0.0.1-SNAPSHOT.jar