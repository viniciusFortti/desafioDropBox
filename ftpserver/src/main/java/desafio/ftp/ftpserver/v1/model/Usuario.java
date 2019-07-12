package desafio.ftp.ftpserver.v1.model;

import lombok.Data;

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
