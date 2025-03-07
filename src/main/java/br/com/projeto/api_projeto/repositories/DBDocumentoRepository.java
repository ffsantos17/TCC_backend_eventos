package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.Documento;
import br.com.projeto.api_projeto.models.DocumentoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBDocumentoRepository implements DocumentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int criar(Documento documento) {
        return jdbcTemplate.update("INSERT INTO documento(documento_modelo, documento_nome, documento_possui_modelo, documento_data_criacao, documento_excluido) VALUES (?,?,?,NOW(),0)",
                documento.getModelo(), documento.getNome(), documento.isPossuiModelo());
    }

    @Override
    public Documento buscarPorId(int id) {
        try {
            Documento documento = jdbcTemplate.queryForObject("SELECT documento_id as id, documento_modelo as modelo, documento_nome as nome, documento_possui_modelo as possuiModelo, documento_data_criacao as dataCriacao, documento_excluido as excluido FROM documento WHERE documento_id=?",
                    BeanPropertyRowMapper.newInstance(Documento.class), id);
            return documento;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public DocumentoUsuario entregarDocumento(String nomeArquivo, int documentoUsuarioId) {

        jdbcTemplate.update("UPDATE usuario_r_documento SET entregue=1,anexo_Nome=?,anexo_Data=NOW(), anexo_visualizado=0 WHERE id=?", nomeArquivo, documentoUsuarioId);
        return null;
    }

    @Override
    public boolean visualizarDocumento(int documentoUsuarioId) {
        try {
        jdbcTemplate.update("UPDATE usuario_r_documento SET anexo_visualizado=1 WHERE id=?", documentoUsuarioId);
        return true;
        }catch (IncorrectResultSizeDataAccessException e){
            return false;
        }
    }

    @Override
    public DocumentoUsuario removerDocumento(int documentoUsuarioId) {

        jdbcTemplate.update("UPDATE usuario_r_documento SET entregue=0,anexo_Nome='',anexo_Data=NULL WHERE id=?", documentoUsuarioId);
        return null;
    }

    @Override
    public List<Documento> buscarTodos() {
        return jdbcTemplate.query("SELECT documento_id AS id, documento_nome as nome, documento_possui_modelo AS possuiModelo, documento_modelo AS modelo, documento_data_criacao AS dataCriacao FROM documento WHERE documento_excluido=0;", BeanPropertyRowMapper.newInstance(Documento.class));

    }
}
