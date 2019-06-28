package desafio.ftp.ftpserver.ServerFTP;

import org.apache.ftpserver.ftplet.UserManager;


public interface UserManagerFactory {

    UserManager createUserManager();
}