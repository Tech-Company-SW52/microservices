FROM openjdk:17-alpine
COPY ./build/libs/registry-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch registry-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/registry-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /registry-service-0.0.1-SNAPSHOT.jar