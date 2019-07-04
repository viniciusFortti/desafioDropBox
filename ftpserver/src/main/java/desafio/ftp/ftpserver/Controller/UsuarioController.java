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

    @PostMapping(value = "")
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @GetMapping(value = "/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable int id) {
        return usuarioService.buscarUsuario(id);
    }

    @DeleteMapping(value = "")
    public Usuario removerUsuario( @RequestBody Usuario usuario) {
        return usuarioService.removerUsuario(usuario);
    }

    @PutMapping(value ="")
    public Usuario editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario) {
        return usuarioService.editarUsuario(id,usuario);
    }

    @GetMapping(value = "")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

}