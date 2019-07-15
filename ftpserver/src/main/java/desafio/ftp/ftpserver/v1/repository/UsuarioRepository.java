package desafio.ftp.ftpserver.v1.repository;

import desafio.ftp.ftpserver.v1.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario,Long> {

    List<Usuario> findByNomeContainingIgnoreCase(String nome);



    Usuario findByNome(String nome);


}
