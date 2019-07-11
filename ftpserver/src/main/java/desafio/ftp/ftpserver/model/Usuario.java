package desafio.ftp.ftpserver.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;


@Data
public class Usuario {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private List<Usuario> amigos;


}
