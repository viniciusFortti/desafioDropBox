package desafio.ftp.ftpserver.v1.service;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ArquivoService {

    @Autowired
    UsuarioService usuarioService;


    public boolean enviar(MultipartFile arquivo, String usuario, String senha) throws IOException {
        FTPClient con = ServiceUtil.conexao(usuario,senha);
        return con.storeFile(arquivo.getOriginalFilename(), arquivo.getInputStream());
        }


    public Boolean deletar(String nomeArquivo,String usuario, String senha) throws IOException {
        FTPClient con = ServiceUtil.conexao(usuario,senha);
        return con.deleteFile(nomeArquivo);
    }

    public FTPFile[] listar(String usuario, String senha) throws IOException {
        FTPClient con = ServiceUtil.conexao(usuario,senha);
        return con.listFiles();

    }

    public void download(String usuario, String senha, String nome) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/home/technocorp/Downloads/"+nome);
        FTPClient con = ServiceUtil.conexao(usuario,senha);
        con.retrieveFile(nome, fileOutputStream);
    }

    public void compartilhar(String usuario, String senha){
        FTPClient con = ServiceUtil.conexao(usuario,senha);

    }

    public Page<FTPFile> listarPaginado(int pagina, int quantidade, String usuario, String senha){

        FTPClient con = ServiceUtil.conexao(usuario,senha);

        try {
            return ServiceUtil.paginacao(con.listFiles(),pagina,quantidade);

        }catch (IOException e) {
            e.getMessage();
            return  null;
        }
    }


}