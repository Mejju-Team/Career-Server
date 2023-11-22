FROM openjdk:17-alpine

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar
#
#RUN mkdir src/main/resources
#
#RUN cp /var/jenkins/gradle/*es gradle/wrapper
#
#RUN cp /var/jenkins/app/*es src/main/resources

ENTRYPOINT ["java","-jar","/app.jar"]