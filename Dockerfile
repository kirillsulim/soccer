FROM openjdk:8-slim
MAINTAINER Kirill Sulim <kirillsulim@gmail.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/soccer/soccer.jar"]

#ADD ./target/lib /usr/share/soccer/lib

ARG JAR_FILE
ADD ./target/${JAR_FILE} /usr/share/soccer/soccer.jar
