package desafio.ftp.ftpserver.controller;

import desafio.ftp.ftpserver.model.Usuario;
import desafio.ftp.ftpserver.service.UploadService;
import desafio.ftp.ftpserver.service.UsuarioService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value ="/uploads")
public class ArquivoController {


    @Autowired
    UsuarioService usuarioService ;

    @Autowired
    UploadService uploadService;


    @PostMapping(value = "/{nome}")
    public String uploadArquivo(@RequestParam("file") MultipartFile arquivo,@PathVariable String nome) throws IOException, FtpException {

        Usuario usuario = usuarioService.buscarUsuario(nome);

        uploadService.upload(arquivo,usuario.getNome(),usuario.getSenha());

        return "arquivo enviado";
    }
}
