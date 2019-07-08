package desafio.ftp.ftpserver.Repository;

import desafio.ftp.ftpserver.Model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario,Long> {

    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    Usuario findByNome(String nome);


}
