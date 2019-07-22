package desafio.ftp.ftpserver.v1.service;

import desafio.ftp.ftpserver.v1.DTO.ArquivoDTO;
import desafio.ftp.ftpserver.v1.model.Usuario;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUtil {

    @Autowired
    UsuarioService usuarioService;


    public static FTPClient conexao(String usuario, String senha) {
        FTPClient con = new FTPClient();
        try {
            con.connect("127.0.0.1", 2021);
            con.login(usuario, senha);
        } catch (IOException e) {
            e.getMessage();
        }
        return con;
    }

    public static Page<ArquivoDTO> paginacao(ArrayList<ArquivoDTO> arquivos, int pagina, int quantidade) {

        List<ArquivoDTO> listaArquivos = new ArrayList<>();

        for (ArquivoDTO arquivo : arquivos) {
            listaArquivos.add(arquivo);
        }
        int maximoArquivos = ((quantidade*pagina)  > listaArquivos.size()) ? listaArquivos.size() : quantidade * (pagina);

        Page<ArquivoDTO> paginaArquivos = new PageImpl<>(listaArquivos.subList((pagina - 1) * quantidade, maximoArquivos), PageRequest.of(pagina, quantidade), maximoArquivos);

        return paginaArquivos;
    }

    public boolean verificaAmigo(Long idAmigo, Long id) {
        Optional<Usuario> amigoAuxiliar = usuarioService.buscaUsuario(idAmigo);
        Usuario amigo = amigoAuxiliar.get();

        Optional<Usuario> usuarioAuxiliar = usuarioService.buscaUsuario(id);
        Usuario usuario = usuarioAuxiliar.get();

        if (usuario.getAmigos().contains(amigo.getId())) {
            return true;
        }
        return false;
    }

}
