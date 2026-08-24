FROM eclipse-temurin:21-alpine

WORKDIR /app

RUN addgroup -S userservice && adduser -S userservice -G userservice

COPY target/userservice-merezh-0.0.1-SNAPSHOT.jar /app/userservice-merezh.jar

RUN chown -R userservice:userservice /app

USER userservice

ENTRYPOINT ["java", "-jar", "userservice-merezh.jar"]