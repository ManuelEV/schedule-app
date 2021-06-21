FROM maven:3.6.1-amazoncorretto-11

WORKDIR /usr/src/java-code
COPY . /usr/src/java-code
RUN mvn package

CMD ["java", "-jar", "/usr/src/java-code/target/schedule-backend-0.0.1-SNAPSHOT.jar"]