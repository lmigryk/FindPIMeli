FROM openjdk:11-jre-slim-buster
EXPOSE 8089
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]