package desafio.ftp.ftpserver.exceptions;

import desafio.ftp.ftpserver.model.Usuario;
import desafio.ftp.ftpserver.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtil {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void verificaUsuarioId(Long id){
        if(!usuarioRepository.existsById(id)){
          throw new ResourceNotFoundException("Usuario não encontrado com ID : " + id);
        }
    }

    public void verificaUsuarioNome(String nome){
        Usuario usuario = usuarioRepository.findByNome(nome);
        if(usuario == null) {
            throw new ResourceNotFoundException("Nenhum usuario encontrado com o parametro " + nome);
        }
    }

    public void verificaCamposUsuarios(Usuario usuario) {

        Boolean nomeUsuario = usuario.getNome() == null;
        Boolean cpfUsuario = usuario.getCpf() == null;
        Boolean emailUsuario = usuario.getEmail() == null;
        Boolean senhaUsuario = usuario.getSenha() == null;

        if(nomeUsuario || cpfUsuario || emailUsuario ||senhaUsuario ){
            throw new ResourceNotFoundException("Campo de usuario nulo, verifique sua requisição");
        }
    }
}
