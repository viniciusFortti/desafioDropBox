package desafio.ftp.ftpserver.v1.DTO;

import desafio.ftp.ftpserver.v1.model.Usuario;
import lombok.Data;

import java.util.List;
@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private List<Long> amigos;

    public UsuarioDTO(Usuario usuario) {
        this.setId(usuario.getId());
        this.setNome(usuario.getNome());
        this.setCpf(usuario.getCpf());
        this.setEmail(usuario.getEmail());
        this.setAmigos(usuario.getAmigos());
    }

}
