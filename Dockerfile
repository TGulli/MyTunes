FROM openjdk:14
ADD target/MyTunes.jar mytunes_docker.jar
ENTRYPOINT ["java", "-jar", "/mytunes_docker.jar"]