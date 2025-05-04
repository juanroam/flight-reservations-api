FROM openjdk:17-alpine AS builder
WORKDIR /workspace
ARG JAR_FILE=build/libs/*[!-plain].jar
COPY ${JAR_FILE} flight-reservations-api.jar
RUN java -Djarmode=layertools -jar flight-reservations-api.jar extract

FROM openjdk:17-alpine
WORKDIR /workspace
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher", "--spring.config.location=classpath:/application-docker.yml"]