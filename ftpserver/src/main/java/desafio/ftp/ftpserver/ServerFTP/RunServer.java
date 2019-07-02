package desafio.ftp.ftpserver.ServerFTP;


import org.apache.ftpserver.ftplet.FtpException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class RunServer {

    private static final Logger logger = LoggerFactory.getLogger(RunServer.class);

    private ServerSocket socket;

    private static ConfigurationServer serverFtp = new ConfigurationServer();


    public RunServer() throws IOException, FtpException {
        this.socket = new ServerSocket(8081);
    }

    public static void run() throws FtpException, IOException {

        serverFtp.start();
    }

    public static void restart() throws FtpException {
        serverFtp.stop();
        serverFtp.start();
    }


}
