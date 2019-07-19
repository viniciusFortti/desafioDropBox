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

    //TODO verificar forma correta de validar arquivo
    public void extensaoArquivoInexistente( MultipartFile arquivo) {
    if (!arquivo.getOriginalFilename().contains(".")) {
            throw new MultiPartException("arquivo sem sua respectiva extensão verifique sua solicitação.");
        }
    }

    //TODO verificar forma correta de validar arquivo
    public void arquivoVazio( MultipartFile arquivo) {
        if (arquivo.getSize() == 0) {
            throw new MultiPartException("Arquivo vazio verifique sua solicitação.");
        }
    }
}
