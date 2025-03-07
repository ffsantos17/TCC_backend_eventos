package br.com.projeto.api_projeto.controller;

import br.com.projeto.api_projeto.config.TokenService;
import br.com.projeto.api_projeto.models.Usuario;
import br.com.projeto.api_projeto.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/registrar")
    public ResponseEntity<String> addNewUser(Usuario userInfo) {
        if(usuarioRepository.buscarUsuario(userInfo.getEmail(), userInfo.getCpf(), userInfo.getMatricula()) != null){
            return new ResponseEntity<>("Usuário já cadastrado", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            try {
                String senha = userInfo.getSenha();
                userInfo.setSenha(passwordEncoder.encode(userInfo.getSenha()));
                int insert = usuarioRepository.salvar(userInfo);
                if(insert == 1) {
                    var usernamePassword = new UsernamePasswordAuthenticationToken(userInfo.getEmail(), senha);
                    var auth = this.authenticationManager.authenticate(usernamePassword);
                    var token = tokenService.generateToken((Usuario) auth.getPrincipal());

                    return ResponseEntity.ok(token);
                }
                return new ResponseEntity<>("Erro ao criar usuário", HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(Usuario userInfo){

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(userInfo.getEmail(), userInfo.getSenha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());

            return ResponseEntity.ok(token);
        } catch (Exception e){
            return new ResponseEntity<>("Usuario ou senha invalidos "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping(value = "/obter-usuario", produces = "application/json; charset=utf-8" )
    public ResponseEntity<Usuario> getUser(@RequestHeader("Authorization") String bearerToken){
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String jwtToken = bearerToken.substring(7);
            String username = tokenService.validateToken(jwtToken);
            Usuario userDetails = usuarioRepository.buscarPorEmail(username);
            userDetails.setEventos(usuarioRepository.buscarEventosUsuarios(userDetails.getId()));
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
