package desafio.ftp.ftpserver.ServerFTP;

import desafio.ftp.ftpserver.Model.Usuario;
import desafio.ftp.ftpserver.Service.FtpService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.ftpserver.ftplet.FtpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RunServer {

    @Autowired
    private FtpService ftpService;

    private static final Logger logger = LoggerFactory.getLogger(RunServer.class);

    private ServerSocket socket;

    public RunServer() throws IOException, FtpException {
        this.socket = new ServerSocket(8081);
    }

    public static void run() throws FtpException, IOException {

        ConfigurationServer serverFtp = new ConfigurationServer();
        serverFtp.start();
    }


}
