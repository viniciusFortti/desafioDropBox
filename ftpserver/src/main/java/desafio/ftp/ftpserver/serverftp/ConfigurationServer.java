package desafio.ftp.ftpserver.serverftp;

import desafio.ftp.ftpserver.login.Login;
import desafio.ftp.ftpserver.login.UserManagerCustom;

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

    public boolean start() {
        serverFactory = new FtpServerFactory();

        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(8081);
        listenerFactory.setIdleTimeout(120);

        ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
        connectionConfigFactory.setAnonymousLoginEnabled(false);

        Map<String, Ftplet> map = new HashMap<>();
        map.put("FtpletListeners", new Login());

        serverFactory.addListener("default", listenerFactory.createListener());
        serverFactory.setFtplets(map);
        serverFactory.setConnectionConfig(connectionConfigFactory.createConnectionConfig());
        serverFactory.setUserManager(UserManagerCustom.criaUserManager());
        server = serverFactory.createServer();

        try {
            server.start();
        } catch (FtpException e) {
            e.getMessage();
        }
        return true;
    }

    public void stop() {
        if(!server.isStopped()) {
            server.stop();
            server = null;
        }
    }

}
