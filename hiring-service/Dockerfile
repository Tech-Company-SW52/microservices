FROM openjdk:17-alpine
COPY ./build/libs/hiring-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch hiring-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/hiring-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /hiring-service-0.0.1-SNAPSHOT.jar