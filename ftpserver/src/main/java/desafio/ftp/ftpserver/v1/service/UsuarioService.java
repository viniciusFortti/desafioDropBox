package desafio.ftp.ftpserver.v1.service;

import desafio.ftp.ftpserver.v1.model.Usuario;
import desafio.ftp.ftpserver.v1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario editarUsuario(Long id, Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void removerUsuarioId(Long id)  {
        usuarioRepository.deleteById(id);
    }

    public void removerUsuario(Usuario usuario)  {
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> buscarPorNome(String nome){ return usuarioRepository.findByNomeContainingIgnoreCase(nome);}

    public Usuario adicionarAmigo(Long id,Long idAmigo){

        Optional<Usuario> usuarioAuxiliar = buscarUsuario(id);
        Optional<Usuario> amigoAuxiliar = buscarUsuario(idAmigo);

        Usuario usuario = usuarioAuxiliar.get();
        Usuario amigo = amigoAuxiliar.get();

        usuario.getAmigos().add(amigo.getId());

        return salvar(usuario);

    }

    public Usuario deletarAmigo(Long id,Long idAmigo) {

        Optional<Usuario> usuarioAuxiliar = buscarUsuario(id);
        Optional<Usuario> amigoAuxiliar = buscarUsuario(idAmigo);

        Usuario usuario = usuarioAuxiliar.get();
        Usuario amigo = amigoAuxiliar.get();
        if (usuario.getAmigos().contains(amigo)){
        usuario.getAmigos().remove(amigo);
        }

        return salvar(usuario);

    }

}
