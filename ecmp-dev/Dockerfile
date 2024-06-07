FROM amazoncorretto:17

ARG JAR_FILE="ecmp-dev-*.jar"

COPY target/${JAR_FILE} /app/api.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/app/api.jar"]