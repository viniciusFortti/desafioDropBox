package desafio.ftp.ftpserver.login;

import desafio.ftp.ftpserver.serverftp.RunServer;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserManagerCustom {

    private static String nomeUsuario;
    private static boolean novoUsuario;
    private static String senhaUsuario;


    public static UserManager criaUserManager() {
        PropertiesUserManagerFactory umf = new PropertiesUserManagerFactory();
        try {
            new File("myuser.properties").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        umf.setFile(new File("myuser.properties"));
        return umf.createUserManager();
    }


    public static void salvaUsuario(String nome, String senha) throws FtpException {
        UserManager userManager = criaUserManager();

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());

        BaseUser user = new BaseUser();
        user.setAuthorities(authorities);
        user.setName(nome);
        user.setPassword(senha);
        user.setHomeDirectory(getPathUsuario(nome));

        try {
            Runtime.getRuntime().exec("mkdir " + user.getHomeDirectory());
            userManager.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPathUsuario(String nome) {
        return System.getProperty("user.dir") + "/servidorFTP/" + nome;
    }

    public static void criarNovoUsuario(FtpRequest request, String command) throws FtpException {
        if (command.contains("USER")){
            nomeUsuario = request.getArgument();
            novoUsuario = UserManagerCustom.verificaUsuario(nomeUsuario);
        }
        if (command.contains("PASS") && novoUsuario) {
            senhaUsuario = request.getArgument();
            salvaUsuario(nomeUsuario, senhaUsuario);
            RunServer.restart();
            novoUsuario = false;
        }
    }

    public static boolean verificaUsuario(String nome) {
        User usuario = null;
        try {
            usuario = criaUserManager().getUserByName(nome);
        } catch (FtpException e){
            e.getMessage();
        }
        return usuario == null;
    }

}
