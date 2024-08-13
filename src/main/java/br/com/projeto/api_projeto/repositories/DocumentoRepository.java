package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.Documento;
import br.com.projeto.api_projeto.models.DocumentoUsuario;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Repository
public interface DocumentoRepository{

    int criar(Documento documento);

    Documento buscarPorId(int id);

    List<Documento> buscarTodos();

    DocumentoUsuario entregarDocumento(String nomeArquivo, int documentoUsuarioId);

    DocumentoUsuario removerDocumento(int documentoUsuarioId);


}
