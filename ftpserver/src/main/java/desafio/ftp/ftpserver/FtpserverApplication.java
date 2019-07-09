package desafio.ftp.ftpserver;

import desafio.ftp.ftpserver.serverftp.RunServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FtpserverApplication {

    public static void main(String[] args) throws FtpException, IOException {
        SpringApplication.run(FtpserverApplication.class, args);
        RunServer.run();
        }



}
