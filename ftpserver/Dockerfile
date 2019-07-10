FROM openjdk:11
LABEL AUTHOR="Vinicius Pulgatti"
COPY ./build/libs/ /var/www
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "ftpserver.jar" ]
