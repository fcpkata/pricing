FROM openjdk:11-slim-buster
WORKDIR /pricing-app
COPY build/libs/pricing-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar", "pricing-0.0.1-SNAPSHOT.jar"]
