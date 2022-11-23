FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# buildar e rodar
# docker build -t springio/gs-spring-boot-docker .
# docker build -t springio/gs-spring-boot-docker .