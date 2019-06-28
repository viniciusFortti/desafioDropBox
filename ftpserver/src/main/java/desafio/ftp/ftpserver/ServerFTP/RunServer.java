package desafio.ftp.ftpserver.ServerFTP;

import org.apache.ftpserver.ftplet.FtpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RunServer {
    private static final Logger logger = LoggerFactory.getLogger(RunServer.class);

    private ServerSocket socket;

    public RunServer() throws IOException {
        this.socket = new ServerSocket(8081);
    }

    public void escuta() throws IOException {
        Socket servidor = socket.accept();
    }

    public static void run() throws FtpException, IOException {

        ConfigurationServer serverFtp = new ConfigurationServer();
        serverFtp.setPort(8081);
        serverFtp.start();
    }
}
