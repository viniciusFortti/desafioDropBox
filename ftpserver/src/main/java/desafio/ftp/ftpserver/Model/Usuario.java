package desafio.ftp.ftpserver.Model;

import org.springframework.data.annotation.Id;

public class Usuario {

    @Id
    private Long id;

    private String nome;

    private String cpf;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
