FROM openjdk:8
VOLUME /tmp
ADD target/mini-hacker-news-api-1.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]