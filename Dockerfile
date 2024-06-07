FROM gradle:8-jdk17 AS build
LABEL maintainer="HanSanCoder"

WORKDIR /build
COPY . .

RUN gradle build --no-daemon --parallel

FROM node:18 AS front
LABEL maintainer="HanSanCoder"

EXPOSE 3000

WORKDIR /app

COPY --from=build /build/front/dist .

COPY /front/package.json .

ENTRYPOINT ["yarn", "start"]

FROM openjdk:17 AS service-auth
LABEL maintainer="HanSanCoder"

ARG SERVICE_NAME=service-auth

EXPOSE 8080

WORKDIR /app

COPY --from=build /build/$SERVICE_NAME/build/libs/$SERVICE_NAME-*SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

FROM openjdk:17 AS service-user
LABEL maintainer="HanSanCoder"

ARG SERVICE_NAME=service-user

EXPOSE 8080

WORKDIR /app

COPY --from=build /build/$SERVICE_NAME/build/libs/$SERVICE_NAME-*SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

FROM openjdk:17 AS service-exam
LABEL maintainer="HanSanCoder"

ARG SERVICE_NAME=service-exam

EXPOSE 8080

WORKDIR /app

COPY --from=build /build/$SERVICE_NAME/build/libs/$SERVICE_NAME-*SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

FROM openjdk:17 AS service-grade
LABEL maintainer="HanSanCoder"

ARG SERVICE_NAME=service-grade

EXPOSE 8080

WORKDIR /app

COPY --from=build /build/$SERVICE_NAME/build/libs/$SERVICE_NAME-*SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

FROM openjdk:17 AS service-page
LABEL maintainer="HanSanCoder"

ARG SERVICE_NAME=service-page

EXPOSE 8080

WORKDIR /app

COPY --from=build /build/$SERVICE_NAME/build/libs/$SERVICE_NAME-*SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

FROM openjdk:17 AS service-question
LABEL maintainer="HanSanCoder"

ARG SERVICE_NAME=service-question

EXPOSE 8080

WORKDIR /app

COPY --from=build /build/$SERVICE_NAME/build/libs/$SERVICE_NAME-*SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

FROM openjdk:17 AS gateway
LABEL maintainer="HanSanCoder"

ARG SERVICE_NAME=gateway

EXPOSE 80

WORKDIR /app

COPY --from=build /build/$SERVICE_NAME/build/libs/$SERVICE_NAME-*SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]