package desafio.ftp.ftpserver.v1.model;

import desafio.ftp.ftpserver.v1.DTO.UsuarioDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Usuario {

    @Id
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private List<Long> amigos;


    public Usuario(Long id, String nome, String cpf, String email, List<Long> amigos) {
        this.setId(id);
        this.setNome(nome);
        this.setCpf(cpf);
        this.setEmail(email);
        this.setAmigos(amigos);
    }

    public UsuarioDTO transformaParaDto(Usuario usuario){
        return new UsuarioDTO(usuario);
    }
}
