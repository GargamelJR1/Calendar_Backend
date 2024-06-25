FROM eclipse-temurin:21-jdk
RUN mkdir -p /usr/app && \
    curl -o /usr/app/wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /usr/app/wait-for-it.sh
COPY ./target/calendarapi-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
CMD ["/usr/app/wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "calendarapi-0.0.1-SNAPSHOT.jar"]