package desafio.ftp.ftpserver.Controller;

import desafio.ftp.ftpserver.Model.Usuario;
import desafio.ftp.ftpserver.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController{

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(value = "/")
    @ResponseBody
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Optional<Usuario> buscarPorId(@PathVariable int id) {
        return usuarioService.buscarUsuario(id);
    }

    @DeleteMapping
    @ResponseBody
    public void removerUsuario(Usuario usuario) {
         usuarioService.removerUsuario(usuario);
    }

    @GetMapping(value = "/")
    @ResponseBody
    public List<Usuario> buscarAll() {
        return usuarioService.allUsuarios();
    }

}