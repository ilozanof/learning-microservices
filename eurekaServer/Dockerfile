# This Dockerfile creates a Docker image with the EurekaServer app.
# NOTE: This is only for Academic purposes. the best way to generate a Docker image
# is as part of the build process, using a Gradle plugin (See README.MD file).

FROM jeanblanchard/java:8
COPY build/libs/eurekaServer-0.0.1-SNAPSHOT.jar eurekaServer-0.0.1-SNAPSHOT.jar
CMD java -jar eurekaServer-0.0.1-SNAPSHOT.jar
EXPOSE 8761