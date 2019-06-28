package desafio.ftp.ftpserver.ServerFTP;

import desafio.ftp.ftpserver.Model.Usuario;
import org.apache.ftpserver.ftplet.FtpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.impl.BaseUser;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RunServer {
    private static final Logger logger = LoggerFactory.getLogger(RunServer.class);

    private ServerSocket socket;

    public RunServer() throws IOException, FtpException {
        this.socket = new ServerSocket(8081);

        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("/test.txt"));
        UserManager um = userManagerFactory.createUserManager();
        BaseUser user = new BaseUser();
        user.setName("admin");
        user.setPassword("admin");
        um.save(user);
    }

    public void escuta() throws IOException {
        Socket servidor = socket.accept();
    }

    public static void run() throws FtpException, IOException {

        ConfigurationServer serverFtp = new ConfigurationServer();
        serverFtp.setPort(8081);
        serverFtp.start();
    }

   /* public void manager() throws FtpException {
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("/test.txt"));
        UserManager um = userManagerFactory.createUserManager();
        BaseUser user = new BaseUser();
        user.setName("admin");
        user.setPassword("admin");
        um.save(user);
    }*/
}
