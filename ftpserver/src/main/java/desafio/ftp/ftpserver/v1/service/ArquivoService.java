package desafio.ftp.ftpserver.v1.service;

import desafio.ftp.ftpserver.v1.model.Usuario;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ArquivoService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ServiceUtil serviceUtil;

    public boolean enviar(MultipartFile arquivo, String nome, String senha){
        FTPClient con = ServiceUtil.conexao(nome, senha);
        try {
            return con.storeFile(arquivo.getOriginalFilename(), arquivo.getInputStream());
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }


    public Boolean deletar(String nomeArquivo, String nome, String senha)  {
        FTPClient con = ServiceUtil.conexao(nome, senha);
        try {
            return con.deleteFile(nomeArquivo);
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }

    public FTPFile[] listar(String nome, String senha)  {
        FTPClient con = ServiceUtil.conexao(nome, senha);
        try {
            return con.listFiles();
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    public void download(String nome, String senha, String nomeArquivo)  {
        FTPClient con = ServiceUtil.conexao(nome, senha);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/home/technocorp/Downloads/" + nomeArquivo);
            con.retrieveFile(nomeArquivo, fileOutputStream);
        } catch (IOException e) {
            e.getMessage();
        }
    }


    public Page<FTPFile> listarPaginado(int pagina, int quantidade, String nome, String senha) {

        FTPClient con = ServiceUtil.conexao(nome, senha);

        try {
            return ServiceUtil.paginacao(con.listFiles(), pagina, quantidade);

        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    public FTPFile[] compartilharArquivos(Long idAmigo, Long id) {

            if (serviceUtil.verificaAmigo(idAmigo,id)){
                Optional<Usuario> usuario = usuarioService.buscarUsuario(id);

                return listar(usuario.get().getNome(), usuario.get().getSenha());

            }
        return null;
    }
}


