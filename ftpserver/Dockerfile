FROM openjdk:11
LABEL AUTHOR="Vinicius Pulgatti"
COPY ./build/libs /var/www
WORKDIR /var/www
EXPOSE 8080
ENTRYPOINT ["java","-jar","gs-spring-boot-docker-0.1.0.jar"]



