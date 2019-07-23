package desafio.ftp.ftpserver.v1;

import desafio.ftp.ftpserver.v1.serverftp.RunServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FtpserverApplication {

    public static void main(String[] args){
        SpringApplication.run(FtpserverApplication.class, args);
        RunServer.run();
        }
}
