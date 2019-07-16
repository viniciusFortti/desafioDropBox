package desafio.ftp.ftpserver.v1.controller;

import desafio.ftp.ftpserver.v1.login.UserManagerCustom;
import desafio.ftp.ftpserver.v1.model.Usuario;
import desafio.ftp.ftpserver.v1.exceptions.ExceptionUtil;
import desafio.ftp.ftpserver.v1.service.UsuarioService;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController{

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ExceptionUtil exceptionUtil;

    @GetMapping(value = "/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Long id) {
        exceptionUtil.verificaUsuarioId(id);
        return usuarioService.buscarUsuario(id);

    }

    @GetMapping
    public List<Usuario> buscarPorNome(@RequestParam String nome) {
        exceptionUtil.verificaUsuarioNome(nome);
        return usuarioService.buscarPorNome(nome);}

    @GetMapping(value = "/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) throws FtpException {
        UserManagerCustom.salvaUsuario(usuario.getNome(),usuario.getSenha());
        exceptionUtil.verificaCamposUsuarios(usuario);
        return usuarioService.salvar(usuario);
    }

    @PutMapping(value = "/{id}")
    public Usuario editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario) {
        exceptionUtil.verificaCamposUsuarios(usuario);
        return usuarioService.editarUsuario(id,usuario);
    }

    @DeleteMapping(value = "/{id}")
    public void removerUsuarioId(@PathVariable Long id) {
        exceptionUtil.verificaUsuarioId(id);
        usuarioService.removerUsuarioId(id);
    }

    @DeleteMapping
    public void removerUsuario(@RequestBody Usuario usuario) {
        usuarioService.removerUsuario(usuario);
    }

    @PutMapping(value = "/{id}/{idAmigo}")
    public Usuario adicionarAmigo(@PathVariable Long id, @PathVariable Long idAmigo){
        return usuarioService.adicionarAmigo(id,idAmigo);
    }



}