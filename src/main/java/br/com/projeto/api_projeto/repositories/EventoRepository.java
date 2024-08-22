package br.com.projeto.api_projeto.repositories;


import br.com.projeto.api_projeto.models.DocumentosEvento;
import br.com.projeto.api_projeto.models.Evento;
import br.com.projeto.api_projeto.models.ParticipanteEvento;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface EventoRepository{
    int salvar(Evento evento, ArrayList<String> documentos);

    int atualizar(Evento evento);

    Evento buscarPorId(int id);

    int deletarPorId(int id);

    List<Evento> buscarTodos();

    int buscarVagasEvento(int id);

    int inserirVisita(int id);

    int alterarStatusInscricao(int idEventoUsuario, int status_id);

    List<DocumentosEvento> buscarDocumentoEvento(int idEvento);

    List<ParticipanteEvento> buscarParticipanteEvento(int idEvento);
}
