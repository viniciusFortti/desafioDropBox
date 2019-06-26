package desafio.ftp.ftpserver;


import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;

public class serverInit {
    public static void main(String[] args) throws Exception{

        FtpServerFactory serverFactory = new FtpServerFactory();

        ListenerFactory factory = new ListenerFactory();

        // set the port of the listener
        factory.setPort(2221);

        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        // start the server
        FtpServer server = serverFactory.createServer();
        server.start();
    }
}
