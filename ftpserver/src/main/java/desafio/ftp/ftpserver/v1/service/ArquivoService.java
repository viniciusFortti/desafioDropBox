package desafio.ftp.ftpserver.v1.service;

import desafio.ftp.ftpserver.v1.exceptions.ExceptionUtil;
import desafio.ftp.ftpserver.v1.model.Usuario;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Optional;


@Service
public class ArquivoService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ServiceUtil serviceUtil;

    @Autowired
    ExceptionUtil exceptionUtil;

    public boolean enviar(MultipartFile arquivo,Usuario usuario){
        exceptionUtil.verificaUsuarioNome(usuario.getNome());
        FTPClient con = ServiceUtil.conexao(usuario.getNome(), usuario.getSenha());

        try {
            return con.storeFile(arquivo.getOriginalFilename(), arquivo.getInputStream());
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }


    public Boolean deletar(String nomeArquivo,Usuario usuario)  {
        exceptionUtil.verificaUsuarioNome(usuario.getNome());
        FTPClient con = ServiceUtil.conexao(usuario.getNome(), usuario.getSenha());
        try {
            return con.deleteFile(nomeArquivo);
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }

    public FTPFile[] listar(Usuario usuario)  {
        exceptionUtil.verificaUsuarioNome(usuario.getNome());
        FTPClient con = ServiceUtil.conexao(usuario.getNome(), usuario.getSenha());
        try {
            return con.listFiles();
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

        public void download(Usuario usuario, String nomeArquivo)  {
        exceptionUtil.verificaUsuarioNome(usuario.getNome());
        //exceptionUtil.extensaoArquivoInexistente(nomeArquivo);
        FTPClient con = ServiceUtil.conexao(usuario.getNome(), usuario.getSenha());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/home/technocorp/Downloads/" + nomeArquivo);
            con.retrieveFile(nomeArquivo, fileOutputStream);
        } catch (IOException e) {
            e.getMessage();
        }
    }


    public Page<FTPFile> listarPaginado(int pagina, int quantidade, Usuario usuario) {
        exceptionUtil.verificaUsuarioNome(usuario.getNome());
        FTPClient con = ServiceUtil.conexao(usuario.getNome(),usuario.getSenha());

        try {
            return ServiceUtil.paginacao(con.listFiles(), pagina, quantidade);

        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    public FTPFile[] compartilharArquivos(Long idAmigo, Long id) {
        exceptionUtil.verificaUsuarioId(id);
        exceptionUtil.verificaUsuarioId(idAmigo);

            if (serviceUtil.verificaAmigo(idAmigo,id)){
                Optional<Usuario> usuario = usuarioService.buscarUsuario(id);

                return listar(usuario.get());

            }
        return null;
    }
}


