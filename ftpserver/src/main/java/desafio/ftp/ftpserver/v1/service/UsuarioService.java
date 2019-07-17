package desafio.ftp.ftpserver.v1.service;

import desafio.ftp.ftpserver.v1.exceptions.ExceptionUtil;
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

    @Autowired
    ExceptionUtil exceptionUtil;

    public Usuario salvar(Usuario usuario){
        exceptionUtil.verificaCamposUsuarios(usuario);
        return usuarioRepository.save(usuario);}

    public Optional<Usuario> buscarUsuario(Long id) {
        exceptionUtil.verificaUsuarioId(id);
        return usuarioRepository.findById(id);}

    public Usuario editarUsuario(Long id, Usuario usuario) {
        exceptionUtil.verificaCamposUsuarios(usuario);
        usuario.setId(id);
        return usuarioRepository.save(usuario); }

    public void removerUsuarioId(Long id)  {
        exceptionUtil.verificaUsuarioId(id);
        usuarioRepository.deleteById(id);}

    public void removerUsuario(Usuario usuario)  {
        exceptionUtil.verificaCamposUsuarios(usuario);
        usuarioRepository.delete(usuario);}

    public List<Usuario> listarUsuarios() { return usuarioRepository.findAll();}

    public List<Usuario> buscarPorNome(String nome){
        exceptionUtil.verificaUsuarioNome(nome);
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);}

    public Usuario adicionarAmigo(Long id,Long idAmigo){
        exceptionUtil.verificaUsuarioId(id);
        exceptionUtil.verificaUsuarioId(idAmigo);

        Optional<Usuario> usuarioAuxiliar = buscarUsuario(id);
        Usuario usuario = usuarioAuxiliar.get();
        usuario.getAmigos().add(idAmigo);

        return salvar(usuario);

    }

    public Usuario deletarAmigo(Long id,Long idAmigo) {
        exceptionUtil.verificaUsuarioId(id);

        Optional<Usuario> usuarioAuxiliar = buscarUsuario(id);
        Usuario usuario = usuarioAuxiliar.get();

        if (usuario.getAmigos().contains(idAmigo)){
        usuario.getAmigos().remove(idAmigo);
        }

        return salvar(usuario);

    }

}
