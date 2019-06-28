package desafio.ftp.ftpserver.Repository;

import desafio.ftp.ftpserver.Model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario,Long> {

}
