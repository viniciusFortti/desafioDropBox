package desafio.ftp.ftpserver.v1.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    private Long id;
    @NotBlank
    @Size(message = "nome invalido",min=3, max=30)
    private String nome;
    private CharSequence cpf;
    @Email(message = "email incorreto")
    private String email;
    @Size(message = "senha incorreta",min=5, max=30)
    private String senha;

    private List<Long> amigos;
}
