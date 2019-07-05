package desafio.ftp.ftpserver.Controller;

import desafio.ftp.ftpserver.Model.Usuario;
import desafio.ftp.ftpserver.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController{

    @Autowired
    UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuario(id);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @PutMapping
    public Usuario editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario) {return usuarioService.editarUsuario(id,usuario); }

    @DeleteMapping
    public Usuario removerUsuario( @RequestBody Usuario usuario) {return usuarioService.removerUsuario(usuario);}

}