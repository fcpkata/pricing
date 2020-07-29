FROM openjdk:11-slim-buster
WORKDIR /hello-world-app
COPY build/libs/SpringMicroService-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar", "SpringMicroService-0.0.1-SNAPSHOT.jar"]
