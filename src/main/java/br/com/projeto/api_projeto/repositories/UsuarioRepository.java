package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.DocumentoUsuario;
import br.com.projeto.api_projeto.models.Evento;
import br.com.projeto.api_projeto.models.EventosUsuario;
import br.com.projeto.api_projeto.models.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository {
    int salvar(Usuario usuario);

    int atualizar(Usuario usuario);

    Usuario buscarPorId(int id);

    int deletarPorId(int id);

    List<Usuario> buscarTodos();

    Usuario buscarPorEmail(String email);

    Usuario buscarUsuario(String email, String cpf, String matricula);

    List<EventosUsuario> buscarEventosUsuarios(int idUsuario);
    EventosUsuario buscarEventoUsuario(int idEventoUsuario, int usuarioId);
    List<DocumentoUsuario> buscarDocumentosUsuarios(int idEventoUsuario);
    int inscreverEvento(int eventoId, int usuarioId);


}
