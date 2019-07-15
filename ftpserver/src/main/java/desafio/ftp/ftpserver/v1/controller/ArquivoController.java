package desafio.ftp.ftpserver.v1.controller;

import desafio.ftp.ftpserver.v1.service.ArquivoService;
import desafio.ftp.ftpserver.v1.service.UsuarioService;
import desafio.ftp.ftpserver.v1.model.Usuario;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value ="/v1/arquivos")
public class ArquivoController {


    @Autowired
    UsuarioService usuarioService ;

    @Autowired
    ArquivoService arquivoService;

    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity uploadArquivo(@RequestParam("file") MultipartFile arquivo, @PathVariable Long id) throws IOException {
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);

        if(arquivo != null && id > 0) {
            arquivoService.enviar(arquivo, usuario.get().getNome(), usuario.get().getSenha());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean deletaArquivo(@PathVariable Long id,@PathVariable String nome){
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);
        try {
            arquivoService.deletar(nome,usuario.get().getNome(),usuario.get().getSenha());
        } catch (IOException e) {
            e.getMessage();
        } return false;
    }

    @GetMapping(value = "{id}")
    public FTPFile[] listaTodosArquivos(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);
        try {
            return arquivoService.listar(usuario.get().getNome(),usuario.get().getSenha());
        } catch (IOException e) {
            e.getMessage();
        }return null;
    }

    @GetMapping(value = "/{id}/{nome}")
    public void download(@PathVariable Long id, @PathVariable String nome) throws IOException {
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);
        arquivoService.download(usuario.get().getNome(),usuario.get().getSenha(),nome);

    }


    @GetMapping(value = "/paginados/{id}/{paginas}/{quantidade}")
    public ResponseEntity<Page<FTPFile>> listarArquivosPaginados(@PathVariable(value = "id") Long id,
                                                                 @PathVariable(value = "paginas") Integer pagina,
                                                                 @PathVariable(value = "quantidade") Integer quantidade) {
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);

        return new ResponseEntity<>(this.arquivoService.listarPaginado(
                pagina,quantidade,usuario.get().getNome(),usuario.get().getSenha()),HttpStatus.OK);

    }
}
