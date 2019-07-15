FROM openjdk:11
LABEL AUTHOR="Vinicius Pulgatti"
COPY ./build/libs /var/www
WORKDIR /var/www
EXPOSE 8080 27017 2021
ENTRYPOINT ["java","-jar","ftpserver-0.0.1-SNAPSHOT.jar"]



