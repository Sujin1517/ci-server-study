FROM openjdk:17-slim
COPY ./build/libs/*T.jar app.jar
ENV profiles=docker
ENV dbUsername=postgres
ENV dbPassword=1234
ENV serverPort=5432
CMD ["java", "-jar", "-Dspring.profiles.active=${profiles}", "app.jar"]
EXPOSE 8080