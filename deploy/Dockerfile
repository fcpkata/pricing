FROM openjdk:11-slim-buster
WORKDIR /pricing
COPY build/libs/pricingservice-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar", "pricingservice-0.0.1-SNAPSHOT.jar"]
