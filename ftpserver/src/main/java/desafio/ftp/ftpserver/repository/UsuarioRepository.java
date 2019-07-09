package desafio.ftp.ftpserver.repository;

import desafio.ftp.ftpserver.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario,Long> {

    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    Usuario findByNome(String nome);


}
