package desafio.ftp.ftpserver.Repository;

import desafio.ftp.ftpserver.Model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {

}
