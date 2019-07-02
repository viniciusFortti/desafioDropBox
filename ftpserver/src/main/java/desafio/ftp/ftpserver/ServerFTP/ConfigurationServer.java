package desafio.ftp.ftpserver.ServerFTP;

import desafio.ftp.ftpserver.Login.Login;
import desafio.ftp.ftpserver.Login.UserManagerClass;
import org.apache.commons.net.ftp.FTPClient;

import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ftplet.Ftplet;

import java.util.HashMap;
import java.util.Map;


public class ConfigurationServer {


    private FtpServerFactory serverFactory;
    private FtpServer server;
    private static int port = 8081;


    public boolean start() throws FtpException {
        serverFactory = new FtpServerFactory();
        FTPClient ftp = new FTPClient();

        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(port);
        listenerFactory.setIdleTimeout(60);

        ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
        connectionConfigFactory.setAnonymousLoginEnabled(false);
        connectionConfigFactory.setMaxLogins(10);
        connectionConfigFactory.setMaxThreads(10);

        Map<String, Ftplet> map = new HashMap<>();
        map.put("myFtpler", new Login());

        serverFactory.addListener("default", listenerFactory.createListener());
        serverFactory.setFtplets(map);
        serverFactory.setConnectionConfig(connectionConfigFactory.createConnectionConfig());
        serverFactory.setConnectionConfig(connectionConfigFactory.createConnectionConfig());
        serverFactory.setUserManager(UserManagerClass.getUserManager());
        server = serverFactory.createServer();

        try {
            server.start();
        } catch (FtpException e) {
            e.getMessage();
        }
        return true;
    }

    public void stop() {
        if(server != null && !server.isStopped()) {
            server.stop();
            server = null;
        }
    }

}
