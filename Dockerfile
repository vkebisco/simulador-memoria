FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#mvn clean package
#docker build -t memory-simulator-v2 .
#docker run -d -p 8080:8080 vkebisco/memory-simulator