package desafio.ftp.ftpserver.v1.controller;

import desafio.ftp.ftpserver.v1.service.ArquivoService;
import desafio.ftp.ftpserver.v1.service.UsuarioService;
import desafio.ftp.ftpserver.v1.model.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Api
@RestController
@RequestMapping(value ="/v1/arquivos")
public class ArquivoController {


    @Autowired
    UsuarioService usuarioService ;

    @Autowired
    ArquivoService arquivoService;

    @ApiOperation(value = "Salva o arquivo no servidor ftp")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, arquivo enviado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Arquivo ou usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity uploadArquivo(@RequestParam("file") MultipartFile arquivo, @PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);

        if(arquivo != null && id > 0) {
            arquivoService.enviar(arquivo, usuario.get().getNome(), usuario.get().getSenha());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Deleta o arquivo no servidor ftp")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, arquivo deletado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Arquivo ou usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @DeleteMapping(value = "usuario/{id}/arquivo/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean deletaArquivo(@PathVariable Long id,@PathVariable String nome){
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);

        return arquivoService.deletar(nome,usuario.get().getNome(),usuario.get().getSenha());
    }

    @ApiOperation(value = "Lista todos os arquivo que estão no servidor ftp")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, arquivos listados com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Arquivos ou usuario nao localizados revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @GetMapping(value = "{id}")
    public FTPFile[] listaTodosArquivos(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);

            return arquivoService.listar(usuario.get().getNome(),usuario.get().getSenha());
    }

    @ApiOperation(value = "Baixa o arquivo do servidor ftp para maquina local")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message="Ok, arquivo baixado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Arquivo ou usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @GetMapping(value = "usuario/{id}/arquivo/{nome}")
    public void download(@PathVariable Long id, @PathVariable String nome){
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);
        arquivoService.download(usuario.get().getNome(),usuario.get().getSenha(),nome);

    }

    @ApiOperation(value = "Lista todos os arquivos paginados")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, arquivo listados e paginados com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Arquivo ou usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @GetMapping(value = "/usuario/{id}/paginas/{paginas}/arquivos/{quantidade}")
    public ResponseEntity<Page<FTPFile>> listarArquivosPaginados(@PathVariable(value = "id") Long id,
                                                                 @PathVariable(value = "paginas") Integer pagina,
                                                                 @PathVariable(value = "quantidade") Integer quantidade) {
        Optional<Usuario> usuario = usuarioService.buscarUsuario(id);

        return new ResponseEntity<>(this.arquivoService.listarPaginado(
                pagina,quantidade,usuario.get().getNome(),usuario.get().getSenha()),HttpStatus.OK);

    }

    @ApiOperation(value = "Lista todos os arquivos de um amigo")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, arquivos do amigo listados com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "amigo ou usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @GetMapping(value = "amigo/{idAmigo}/usuario/{id}")
    public FTPFile[] listarArquivosAmigo(@PathVariable Long idAmigo, @PathVariable Long id){

        return arquivoService.compartilharArquivos(idAmigo,id);

    }

}
