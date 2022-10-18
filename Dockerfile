FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} hazelcast-demo.jar
ENTRYPOINT ["java", "-jar", "hazelcast-demo.jar"]