FROM openjdk:17-alpine
COPY ./build/libs/search-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch search-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/search-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /search-service-0.0.1-SNAPSHOT.jar