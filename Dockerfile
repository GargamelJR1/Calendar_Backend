FROM eclipse-temurin:21-jdk
COPY ./target/calendarapi-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
CMD ["java", "-jar", "calendarapi-0.0.1-SNAPSHOT.jar"]