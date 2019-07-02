package desafio.ftp.ftpserver.Login;

import desafio.ftp.ftpserver.ServerFTP.RunServer;
import org.apache.ftpserver.usermanager.*;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.ftplet.UserManager;

import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserManagerClass {

    private static String name;
    private static boolean novo;
    private static String senha;


    public static UserManager getUserManager() {
        PropertiesUserManagerFactory umf = new PropertiesUserManagerFactory();
        try {
            new File("myuser.properties").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        umf.setFile(new File("myuser.properties"));
        umf.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        return umf.createUserManager();
    }


    private static Map<String, String> salvaUsuario(String nome, String senha) throws FtpException {
        UserManager userManager = getUserManager();
        BaseUser user = new BaseUser();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);
        user.setName(nome);
        user.setPassword(senha);
        String patchUsuario = getPathUsuario(nome);
        user.setHomeDirectory(patchUsuario);
        try {
            Runtime.getRuntime().exec("mkdir " + patchUsuario);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        String msg = "";
        userManager.save(user);
        map.put("mensagem", msg);
        return map;
    }


    public static boolean criarNovoUsuario(FtpRequest request, String command) {
        if (command.contains("USER")){

            name = request.getArgument();
            novo = UserManagerClass.verificaUsuario(name);
            return true;
        }
        if (command.contains("PASS") && novo) {
            senha = request.getArgument();
            try {
                Map<String, String> mapUser = salvaUsuario(name, senha);
                RunServer.restart();
            } catch (FtpException e) {
                e.printStackTrace();
            }
            novo = false;
        }
        return false;
    }

    public static boolean verificaUsuario(String nome) {
        User usuario = null;
        try {
            usuario = getUserManager().getUserByName(nome);
        } catch (FtpException e){
            e.getMessage();
        }
        return usuario == null;
    }

    private static String getPathUsuario(String nome) {
        return System.getProperty("user.dir") + "/servidorFTP/" + nome;
    }
}
