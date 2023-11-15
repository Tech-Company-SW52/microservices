FROM openjdk:17-alpine
COPY ./build/libs/admin-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch admin-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/admin-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /admin-service-0.0.1-SNAPSHOT.jar