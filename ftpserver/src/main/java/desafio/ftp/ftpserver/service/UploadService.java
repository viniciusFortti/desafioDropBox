package desafio.ftp.ftpserver.service;

import desafio.ftp.ftpserver.serverftp.ConfigurationServer;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadService {

    @Autowired
    UsuarioService usuarioService;


    public void upload(MultipartFile arquivo,String usuario, String senha) throws IOException {

        FTPClient con = new FTPClient();
        con.connect("127.0.0.1",2021);
        con.login(usuario,senha);

        con.storeFile(arquivo.getOriginalFilename(), arquivo.getInputStream());
    }
}
