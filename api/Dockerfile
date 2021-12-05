FROM maven:3.6-jdk-11
WORKDIR /app

COPY api/src api/src
COPY api/pom.xml api/pom.xml

COPY shared/src shared/src
COPY shared/pom.xml shared/pom.xml

COPY worker/src worker/src
COPY worker/pom.xml worker/pom.xml

COPY pom.xml pom.xml

RUN mvn clean package -pl api,shared

FROM openjdk:11.0-jre
WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/api-0.0.1-SNAPSHOT.jar"]

