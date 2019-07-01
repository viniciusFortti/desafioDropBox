package desafio.ftp.ftpserver.Service;


import desafio.ftp.ftpserver.Model.Usuario;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UsuarioServiceFTP {

    @Autowired
    private FtpService ftpService;

    public  Usuario connect(String username, String password) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("localhost", 2221);
        ftpClient.login(username, password);

        String id = RandomStringUtils.random(20, true, true);
        ftpService.addClient(id, ftpClient);

        Usuario user = new Usuario();
        user.setId(id);
        user.setNome(username);
        return user;
    }
}
