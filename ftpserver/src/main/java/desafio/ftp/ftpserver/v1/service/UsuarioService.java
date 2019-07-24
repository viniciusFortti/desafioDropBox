package desafio.ftp.ftpserver.v1.service;

import desafio.ftp.ftpserver.v1.DTO.UsuarioDTO;
import desafio.ftp.ftpserver.v1.exceptions.ExceptionUtil;
import desafio.ftp.ftpserver.v1.login.UserManagerCustom;
import desafio.ftp.ftpserver.v1.model.Usuario;
import desafio.ftp.ftpserver.v1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ExceptionUtil exceptionUtil;

    public UsuarioDTO salvar(Usuario usuario){
        exceptionUtil.verificaCamposUsuarios(usuario);
        UserManagerCustom.salvaUsuario(usuario.getNome(),usuario.getSenha());
        usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return usuarioDTO;
    }

    public UsuarioDTO buscarUsuario(Long id) {
        exceptionUtil.verificaUsuarioId(id);
        Optional<Usuario> usuarioAux = usuarioRepository.findById(id);
        UsuarioDTO usuario = new UsuarioDTO(usuarioAux.get());
        return usuario;
    }

    public Optional<Usuario> buscaUsuario(Long id) {
        exceptionUtil.verificaUsuarioId(id);
        return usuarioRepository.findById(id);
    }

    public UsuarioDTO editarUsuario(Long id, Usuario usuario) {
        exceptionUtil.verificaCamposUsuarios(usuario);
        usuario.setId(id);
        usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return usuarioDTO;
    }

    public void removerUsuarioId(Long id)  {
        exceptionUtil.verificaUsuarioId(id);
        usuarioRepository.deleteById(id);}

    public void removerUsuario(Usuario usuario)  {
        exceptionUtil.verificaCamposUsuarios(usuario);
        usuarioRepository.delete(usuario);}

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuariosAux = usuarioRepository.findAll();
        List<UsuarioDTO> usuarios = new ArrayList<>();
        for (Usuario usuarioAux : usuariosAux) {
            UsuarioDTO usuario= new UsuarioDTO(usuarioAux);
            usuarios.add(usuario);
        }
        return usuarios;
    }
    public List<UsuarioDTO> buscarPorNome(String nome){
        List<Usuario> usuariosAux = usuarioRepository.findByNomeContainingIgnoreCase(nome);
        List<UsuarioDTO> usuarios = new ArrayList<>();
        for (Usuario usuarioAux : usuariosAux) {
            UsuarioDTO usuario= new UsuarioDTO(usuarioAux);
            usuarios.add(usuario);
        }

        return usuarios;
    }

    public UsuarioDTO adicionarAmigo(Long id,Long idAmigo){
        exceptionUtil.verificaUsuarioId(id);
        exceptionUtil.verificaUsuarioId(idAmigo);

        Optional<Usuario> usuarioAuxiliar = usuarioRepository.findById(id);
        Usuario usuario = usuarioAuxiliar.get();
        usuario.getAmigos().add(idAmigo);
        return salvar(usuario);

    }

    public UsuarioDTO deletarAmigo(Long id,Long idAmigo) {
        exceptionUtil.verificaUsuarioId(id);

        Optional<Usuario> usuarioAuxiliar = usuarioRepository.findById(id);
        Usuario usuario = usuarioAuxiliar.get();

        if (usuario.getAmigos().contains(idAmigo)){
        usuario.getAmigos().remove(idAmigo);
        }

        return salvar(usuario);

    }

}
