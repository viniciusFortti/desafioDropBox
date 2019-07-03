package desafio.ftp.ftpserver.Service;

import desafio.ftp.ftpserver.Model.Usuario;
import desafio.ftp.ftpserver.Repository.UsuarioRepository;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional(rollbackFor = Exception.class)
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @SuppressWarnings("unused")
    public Optional<Usuario> buscarUsuario(long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Usuario editarUsuario(String id, Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removerUsuario( Usuario usuario)  {
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> allUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

}
