package desafio.ftp.ftpserver.v1.service;

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

    public static Page<FTPFile> paginacao(FTPFile[] arquivos, int pagina, int quantidade) {

        List<FTPFile> listaArquivos = new ArrayList<>();

        for (FTPFile arquivo : arquivos) {
            listaArquivos.add(arquivo);
        }
        int maximoArquivos = (quantidade * (pagina + 1) > listaArquivos.size()) ? listaArquivos.size() : quantidade * (pagina + 1);

        Page<FTPFile> paginaArquivos = new PageImpl<>(listaArquivos.subList(pagina * quantidade, maximoArquivos), PageRequest.of(pagina, quantidade), maximoArquivos);

        return paginaArquivos;
    }

    public boolean verificaAmigo(Long id, Long idAmigo) {
        Optional<Usuario> usuarioAuxiliar = usuarioService.buscarUsuario(id);
        Usuario usuario = usuarioAuxiliar.get();

        Optional<Usuario> amigoAuxiliar = usuarioService.buscarUsuario(idAmigo);
        Usuario amigo = amigoAuxiliar.get();

        if (usuario.getAmigos().contains(amigo))
            return true;
        else return false;

    }
}
