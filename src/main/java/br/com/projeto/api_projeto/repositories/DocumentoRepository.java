package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.Documento;
import br.com.projeto.api_projeto.models.DocumentoUsuario;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public interface DocumentoRepository{
    Documento buscarPorId(int id);

    DocumentoUsuario entregarDocumento(String nomeArquivo, int documentoUsuarioId);

    DocumentoUsuario removerDocumento(int documentoUsuarioId);


}
