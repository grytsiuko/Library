FROM maven:3.6-jdk-11 as builder
WORKDIR /app

COPY api/src api/src
COPY api/pom.xml api/pom.xml

COPY shared/src shared/src
COPY shared/pom.xml shared/pom.xml

COPY worker/src worker/src
COPY worker/pom.xml worker/pom.xml

COPY pom.xml pom.xml

RUN mvn test -pl worker,shared
RUN mvn clean package -pl worker,shared

FROM openjdk:11.0-jre
WORKDIR /app

COPY --from=builder /app/worker/target/worker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5000

ENTRYPOINT ["java","-jar","app.jar"]

