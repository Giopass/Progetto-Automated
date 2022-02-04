FROM maven:3.8.4-openjdk-17

MAINTAINER giovanni passaro <g.passaro1@studenti.unimol.it>

ENV HOME=/home/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

##ADD pom.xml $HOME

##RUN ["/usr/local/bin/mvn-entrypoint.sh", mvn, "verify", "clean", "--fail-never"]

ADD . $HOME

RUN ["mvn", "-B", "install"]

FROM ubuntu:8.5-jdk8-openjdk-slim-buster

COPY --from=builder /home/usr/app/target/understability-evalution-0.0.1-SNAPSHOT.war /usr/local/ubuntu/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
