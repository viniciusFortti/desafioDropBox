package desafio.ftp.ftpserver.v1.controller;

import desafio.ftp.ftpserver.v1.DTO.UsuarioDTO;
import desafio.ftp.ftpserver.v1.login.UserManagerCustom;
import desafio.ftp.ftpserver.v1.model.Usuario;
import desafio.ftp.ftpserver.v1.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/usuarios")
@Api(value = "Crud usuario banco mongo")
public class UsuarioController{

    @Autowired
    UsuarioService usuarioService;


    @ApiOperation(value = "Busca o usuario recebendo apenas seu ID")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, usuario encontrado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @GetMapping(value = "/{id}")
    public UsuarioDTO buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuario(id);

    }

    @ApiOperation(value = "Busca o usuario recebendo apenas seu nome")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, usuario encontrado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @GetMapping
    public List<UsuarioDTO> buscarPorNome(@RequestParam String nome) {
        return usuarioService.buscarPorNome(nome);}

    @ApiOperation(value = "Lista todos usuarios")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, usuarios listados com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Usuarios nao localizados"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @GetMapping(value = "/listar")
    public List<UsuarioDTO>  listarUsuarios() {

        return usuarioService.listarUsuarios();
    }

    @ApiOperation(value = "Salva usuario recebendo seus dados")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, usuario salvo com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @PostMapping
    public UsuarioDTO salvar(@RequestBody Usuario usuario){
        UserManagerCustom.salvaUsuario(usuario.getNome(),usuario.getSenha());
        return usuarioService.salvar(usuario);
    }

    @ApiOperation(value = "Edita o usuario com seu id e seus dados a alterar")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, usuario editado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @PutMapping(value = "/{id}")
    public UsuarioDTO editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario) {
        return usuarioService.editarUsuario(id,usuario);
    }

    @ApiOperation(value = "Deleta o usuario recebendo seu ID")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, usuario deletado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity removerUsuarioId(@PathVariable Long id) {
        usuarioService.removerUsuarioId(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta usuario recebendo todos seus dados")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, usuario deletado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @DeleteMapping
    public ResponseEntity removerUsuario(@RequestBody Usuario usuario) {
        usuarioService.removerUsuario(usuario);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona um amigo ao usuario, recebendo os ID's")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, amigo adicionado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Amigo ou usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @PutMapping(value = "usuario/{id}/amigo/{idAmigo}")
    public UsuarioDTO adicionarAmigo(@PathVariable Long id, @PathVariable Long idAmigo){
        return usuarioService.adicionarAmigo(id,idAmigo);
    }

    @ApiOperation(value = "deleta usuario da lista de amigos de outro usuario recebendo os ID's")
    @ApiResponses(value= {
            @ApiResponse(code = 200,message= "Ok, amigo deletado com sucesso."),
            @ApiResponse(code = 401, message = "Autorização incorreta, revise seu login"),
            @ApiResponse(code = 403, message = "Recurso bloqueado para seu login"),
            @ApiResponse(code = 404, message = "Amigo ou Usuario nao localizado revise os parametros"),
            @ApiResponse(code = 500,message= "Ocorreu um erro no servidor.")})
    @PostMapping(value = "usuario/{id}/amigo/{idAmigo}")
    public UsuarioDTO deletarAmigo(@PathVariable Long id, @PathVariable Long idAmigo){
        return usuarioService.deletarAmigo(id,idAmigo);

    }



}