package desafio.ftp.ftpserver.service;

import desafio.ftp.ftpserver.model.Usuario;
import desafio.ftp.ftpserver.repository.UsuarioRepository;
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

    public void removerUsuarioId( Long id)  {
        usuarioRepository.deleteById(id);
    }

    public void removerUsuario(Usuario usuario)  {
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> buscarPorNome(String nome){
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);

    }

}
