FROM openjdk:17-alpine
COPY ./build/libs/personaldata-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch personaldata-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "/personaldata-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /personaldata-service-0.0.1-SNAPSHOT.jar