FROM ubuntu/jdk:25-26.04_edge

LABEL org.opencontainers.image.authors="Sultan Alibekov <sultan-13579@yandex.ru>"

EXPOSE 8080

ENV PROJECT_NAME=booking-system.jar

COPY ./target/${PROJECT_NAME} /app/

ENTRYPOINT ["java","-jar","/app/booking-system.jar"]