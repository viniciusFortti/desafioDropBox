package desafio.ftp.ftpserver.v1.model;

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
}
