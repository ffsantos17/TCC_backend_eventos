package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.Documento;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository{

    Documento buscarPorId(int id);
}
