package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.Alerta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository {
    List<Alerta> listar(int registroId, String tabela);

    int criar(Alerta alerta, int registro_id, String tabela);

    int lerAlerta(int alerta_id);

}
