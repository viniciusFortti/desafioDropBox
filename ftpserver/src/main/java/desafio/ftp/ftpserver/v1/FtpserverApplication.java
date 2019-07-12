package desafio.ftp.ftpserver.v1;

import desafio.ftp.ftpserver.v1.serverftp.RunServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FtpserverApplication {

    public static void main(String[] args) throws FtpException {
        SpringApplication.run(FtpserverApplication.class, args);
        RunServer.run();
        }
}
