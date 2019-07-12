package desafio.ftp.ftpserver.v1.serverftp;

public class RunServer {


    private static ConfigurationServer serverFtp = new ConfigurationServer();

    public RunServer() {

    }

    public static void run() {serverFtp.start();}

    public static void restart() {serverFtp.stop();serverFtp.start();}


}
