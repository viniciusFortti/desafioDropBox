package desafio.ftp.ftpserver.ServerFTP;

import org.apache.ftpserver.ftplet.FtpException;

import java.io.IOException;
import java.net.ServerSocket;

public class RunServer {

    private ServerSocket socket;

    private static ConfigurationServer serverFtp = new ConfigurationServer();

    public RunServer() throws IOException{this.socket = new ServerSocket(8081);}

    public static void run() throws FtpException{serverFtp.start();}

    public static void restart() throws FtpException {serverFtp.stop();serverFtp.start();}


}
