FROM openjdk:17-alpine

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

RUN echo -e "distributionBase=GRADLE_USER_HOME\ndistributionPath=wrapper/dists\ndistributionUrl=https\://services.gradle.org/distributions/gradle-7.6-all.zip\nzipStoreBase=GRADLE_USER_HOME\nzipStorePath=wrapper/dists" > gradle/wrapper/gradle-wrapper.properties

RUN mkdir src/main/resources

RUN cp /var/jenkins/*es src/main/resources

ENTRYPOINT ["java","-jar","/app.jar"]