FROM openjdk:15
ADD target/MyTunes-0.0.1-SNAPSHOT.jar mytunes_docker.jar
ENTRYPOINT ["java", "-jar", "/mytunes_docker.jar"]