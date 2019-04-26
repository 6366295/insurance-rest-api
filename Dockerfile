FROM gradle:5.3.1-jdk8-alpine

ADD --chown=gradle:gradle . /home/gradle
WORKDIR /home/gradle

ENTRYPOINT ["gradle","run", "--stacktrace"]
