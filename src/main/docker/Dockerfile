FROM anapsix/alpine-java
MAINTAINER Andre de Oliveira
ADD /maven/handsdevice-0.0.1-SNAPSHOT.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]
EXPOSE 8080