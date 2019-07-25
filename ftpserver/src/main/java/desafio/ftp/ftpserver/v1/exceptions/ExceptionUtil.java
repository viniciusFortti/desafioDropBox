package desafio.ftp.ftpserver.v1.exceptions;

import desafio.ftp.ftpserver.v1.model.Usuario;
import desafio.ftp.ftpserver.v1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

        if(usuarioRepository.findByNomeContainingIgnoreCase(nome).isEmpty()) {
            throw new ResourceNotFoundException("Nenhum usuario encontrado com : " + nome);
        }
    }

    public void verificaCamposUsuarios(Usuario usuario) {

    }

}
