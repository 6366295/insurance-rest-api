FROM gradle:5.3.1-jdk8-alpine as builder

ADD --chown=gradle:gradle . /home/gradle
WORKDIR /home/gradle

RUN ["gradle","build", "--stacktrace"]

FROM openjdk:8-jdk-alpine

EXPOSE 8443

COPY --from=builder /home/gradle/build/libs/insurance-rest-api-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
