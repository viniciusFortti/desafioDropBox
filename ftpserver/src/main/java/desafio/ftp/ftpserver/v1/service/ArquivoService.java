package desafio.ftp.ftpserver.v1.service;

import desafio.ftp.ftpserver.v1.DTO.ArquivoDTO;
import desafio.ftp.ftpserver.v1.exceptions.ExceptionUtil;
import desafio.ftp.ftpserver.v1.exceptions.ListNotFoundException;
import desafio.ftp.ftpserver.v1.model.Usuario;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.util.ArrayList;
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
        exceptionUtil.extensaoArquivoInexistente(arquivo);
        exceptionUtil.arquivoVazio(arquivo);

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

    public ArrayList<ArquivoDTO> listar(Usuario usuario)  {
        exceptionUtil.verificaUsuarioNome(usuario.getNome());
        FTPClient con = ServiceUtil.conexao(usuario.getNome(), usuario.getSenha());
        try {
            FTPFile[] ftp =  con.listFiles();
            ArrayList<ArquivoDTO> dto = new ArrayList<>();
            for (FTPFile ftpFile : ftp) {
                ArquivoDTO arquivo = new ArquivoDTO(ftpFile);
                dto.add(arquivo);
            }
            return dto;
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }
    @SneakyThrows
    @Synchronized
    public void download(Usuario usuario, String nomeArquivo)  {
        exceptionUtil.verificaUsuarioNome(usuario.getNome());
        @Cleanup FTPClient con = ServiceUtil.conexao(usuario.getNome(), usuario.getSenha());

            FileOutputStream fileOutputStream = new FileOutputStream("/home/technocorp/Downloads/" + nomeArquivo);
            con.retrieveFile(nomeArquivo, fileOutputStream);

    }

    public Page<ArquivoDTO> listarPaginado(int pagina, int quantidade, Usuario usuario) {
        exceptionUtil.verificaUsuarioNome(usuario.getNome());
        FTPClient con = ServiceUtil.conexao(usuario.getNome(),usuario.getSenha());

            return ServiceUtil.paginacao(listar(usuario), pagina, quantidade);

    }

    public ArrayList<ArquivoDTO> compartilharArquivos(Long idAmigo, Long id) {
        exceptionUtil.verificaUsuarioId(id);
        exceptionUtil.verificaUsuarioId(idAmigo);

            if (serviceUtil.verificaAmigo(idAmigo,id)){
                Optional<Usuario> usuario = usuarioService.buscaUsuario(id);
                return listar(usuario.get());

            }
        return null;
    }
}


