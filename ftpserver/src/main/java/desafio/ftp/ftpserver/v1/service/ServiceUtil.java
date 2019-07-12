package desafio.ftp.ftpserver.v1.service;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceUtil {


    public static FTPClient conexao(String usuario,String senha) {
        FTPClient con = new FTPClient();
        try {
            con.connect("127.0.0.1", 2021);
            con.login(usuario, senha);
        } catch (IOException e) {
            e.getMessage();
        }return con;
    }
}
