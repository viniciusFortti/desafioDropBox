package desafio.ftp.ftpserver.controller;

import desafio.ftp.ftpserver.model.Usuario;
import desafio.ftp.ftpserver.service.ArquivoService;
import desafio.ftp.ftpserver.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value ="/arquivos")
public class ArquivoController {


    @Autowired
    UsuarioService usuarioService ;

    @Autowired
    ArquivoService arquivoService;

    //TODO usar log do lombok para retornar mensagem.
    @PostMapping(value = "/{id}")
    public String uploadArquivo(@RequestParam("file") MultipartFile arquivo,@PathVariable Long id) {

        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);
        try {
            arquivoService.upload(arquivo,usuario.get().getNome(),usuario.get().getSenha());
        } catch (IOException e) {
            e.getMessage();
        }

        return "arquivo enviado";
    }
}
