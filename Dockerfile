FROM gradle:8.5.0-jdk17 AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle bootJar --no-daemon

FROM gradle:8.5.0-jdk17

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]