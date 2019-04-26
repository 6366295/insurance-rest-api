FROM openjdk:8-jdk-alpine

COPY . /build
WORKDIR /build
ENTRYPOINT ["./gradlew","run"]
