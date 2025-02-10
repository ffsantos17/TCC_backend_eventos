package br.com.projeto.api_projeto.controller;
import br.com.projeto.api_projeto.models.DocumentoUsuario;
import br.com.projeto.api_projeto.models.EventosUsuario;
import br.com.projeto.api_projeto.models.Usuario;
import br.com.projeto.api_projeto.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;



    @PostMapping(value ="/buscar", produces = "application/json; charset=utf-8" )
    public ResponseEntity<Usuario> buscar(@RequestBody Usuario userInfo){
        Usuario user = usuarioRepository.buscarPorEmail(userInfo.getEmail());
        user.setEventos(usuarioRepository.buscarEventosUsuarios(user.getId()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        try {
            List<Usuario> usuarios = new ArrayList<Usuario>();

            usuarioRepository.buscarTodos().forEach(usuarios::add);
            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listar_por_tipo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> listarUsuariosPorTipo(@RequestHeader("tipoUsuario_Id") int tipoUsuario_Id) {
        try {
            List<Usuario> usuarios = new ArrayList<Usuario>();

            usuarioRepository.buscarPorTipo(tipoUsuario_Id).forEach(usuarios::add);
            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> buscar(@PathVariable int id){
        try {
            Usuario result = usuarioRepository.buscarPorId(id);
            if (result == null) {
                return new ResponseEntity<>("Não foi possivel localizar o usuario id " + id, HttpStatus.OK);
            }
            return new ResponseEntity<>(result.getNome(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Não foi possivel deletar o evento.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value ="/registrar-usuario-evento", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registarUsuarioEvento(@RequestHeader("eventoId") int eventoId,
                                                        @RequestHeader("usuarioId") int usuarioId,
                                                        @RequestHeader("tipoInscricaoId") int tipoInscricaoId,
                                                        @RequestHeader("statusInscricaoId") int statusInscricaoId){
        int response = usuarioRepository.inscreverEvento(eventoId, usuarioId, tipoInscricaoId, statusInscricaoId);
        if(response != 0){
            return new ResponseEntity<>(String.valueOf(response), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("erro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value ="/buscar-evento-usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventosUsuario> buscarEventoUsuario(@RequestHeader("eventoUsuarioId") int eventoUsuarioId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();
        EventosUsuario response = usuarioRepository.buscarEventoUsuario(eventoUsuarioId, usuario.getId());
        if(response == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value ="/buscar-documentos-usuario", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<DocumentoUsuario>> buscarDocumentosUsuario(@RequestHeader("eventoUsuarioId") int eventoUsuarioId){
        List<DocumentoUsuario> response = usuarioRepository.buscarDocumentosUsuarios(eventoUsuarioId);
        if(response == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/editar")
    public ResponseEntity<String> editarUsuario(Usuario userInfo) {
        try {
            int insert = usuarioRepository.atualizar(userInfo);
            if(insert == 1) {
                return new ResponseEntity<>("Sucesso", HttpStatus.OK);
            }
            return new ResponseEntity<>("Erro ao editar usuário", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value ="/deletar", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deletarUsuario(@RequestHeader("usuario_id") int usuarioId){
        int delete = usuarioRepository.deletarPorId(usuarioId);
        if(delete != 1){
            return new ResponseEntity<>("Erro ao Deletar Usuário", HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<>("Sucesso", HttpStatus.OK);
        }
    }

}
