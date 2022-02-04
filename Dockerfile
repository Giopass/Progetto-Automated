FROM maven:3.8.4-openjdk-17 as builder

RUN mkdir -p usr/local/app

COPY . usr/local/app

WORKDIR  usr/local/app

RUN ["mvn", "package"]

FROM openjdk:17

COPY --from=builder usr/local/app/target/Esame_ASD-1.0-SNAPSHOT.jar usr/local/Progetto/EsameASD.jar

WORKDIR /usr/local/Progetto

CMD ["java", "-jar", "EsameASD.jar"]
