FROM openjdk:11
MAINTAINER Leonid
COPY target/tg-serials-bot-0.1.jar app.jar
EXPOSE 7473
ENTRYPOINT ["java", "-jar", "/app.jar"]
