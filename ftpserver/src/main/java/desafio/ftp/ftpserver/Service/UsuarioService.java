package desafio.ftp.ftpserver.Service;

import desafio.ftp.ftpserver.Model.Usuario;
import desafio.ftp.ftpserver.Repository.UsuarioRepository;
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

    public Optional<Usuario> buscarUsuario(long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario editarUsuario(Long id, Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void removerUsuario( Long id)  {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> buscarPorNome(String nome){
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);

    }

}
