package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.Documento;
import br.com.projeto.api_projeto.models.DocumentoUsuario;
import br.com.projeto.api_projeto.models.Evento;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class DBDocumentoRepository implements DocumentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
//    @Override
//    public int salvar(Evento evento) {
//        return jdbcTemplate.update("INSERT INTO evento (evento_data, evento_link, evento_data_criacao, evento_id_usuario_criacao, evento_linkepublico, evento_nome, evento_vagas) VALUES(?,?,?,?,?,?,?)",
//                new Object[] { evento.getData(), evento.getLink(), evento.getDataCriacao(), evento.getIdUsuarioCriacao(), evento.isLinkEPublico(), evento.getNome(), evento.getVagas() });
//    }
//
//    @Override
//    public int atualizar(Evento evento) {
//        return jdbcTemplate.update("UPDATE evento SET evento_link=?,evento_data=?,evento_linkepublico=?,evento_nome=?,evento_vagas=? WHERE evento_Id=?",
//                new Object[] {evento.getLink(), evento.getData(), evento.isLinkEPublico(), evento.getNome(), evento.getVagas(), evento.getId() });
//    }

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

        jdbcTemplate.update("UPDATE `usuario_r_documento` SET entregue=1,anexo_Nome=?,anexo_Data=NOW(), anexo_visualizado=0 WHERE id=?", nomeArquivo, documentoUsuarioId);
        return null;
    }

    @Override
    public boolean visualizarDocumento(int documentoUsuarioId) {
        try {
        jdbcTemplate.update("UPDATE `usuario_r_documento` SET `anexo_visualizado`=1 WHERE `id`=?", documentoUsuarioId);
        return true;
        }catch (IncorrectResultSizeDataAccessException e){
            return false;
        }
    }

    @Override
    public DocumentoUsuario removerDocumento(int documentoUsuarioId) {

        jdbcTemplate.update("UPDATE `usuario_r_documento` SET entregue=0,anexo_Nome='',anexo_Data=NULL WHERE id=?", documentoUsuarioId);
        return null;
    }

    @Override
    public List<Documento> buscarTodos() {
        return jdbcTemplate.query("SELECT documento_id AS id, documento_nome as nome, documento_possui_modelo AS possuiModelo, documento_modelo AS modelo, documento_data_criacao AS dataCriacao FROM documento WHERE documento_excluido=0;", BeanPropertyRowMapper.newInstance(Documento.class));

    }

//    @Override
//    public int deletarPorId(int id) {
//        return jdbcTemplate.update("DELETE FROM evento WHERE evento_id=?", id);
//    }
//
//    @Override
//    public List<Evento> buscarTodos() {
//        return jdbcTemplate.query("SELECT evento.evento_id as id, evento_link as link, evento_data as data, evento_data_criacao as dataCriacao, evento_id_usuario_criacao as idUsuarioCriacao, evento_linkepublico as linkEPublico, evento_nome as nome, evento_vagas as vagas, evento_imagem as imagem, evento_descricao as descricao, evento_visitas as visitas, evento_local as local from evento LEFT JOIN evento_r_usuario AS inscricoes ON evento.evento_id=inscricoes.evento_id AND inscricoes.status NOT IN ('cancelado') where evento_data > NOW() GROUP BY evento.evento_id, evento_link, evento_data, evento_data_criacao, evento_id_usuario_criacao, evento_linkepublico, evento_nome, evento_vagas, evento_imagem having (evento.evento_vagas-COUNT(inscricoes.evento_r_usuario_id)) > 0", BeanPropertyRowMapper.newInstance(Evento.class));
//
//    }
//
//    @Override
//    public int buscarVagasEvento(int id) {
//        String sql = "SELECT (evento.evento_vagas-COUNT(inscricoes.evento_r_usuario_id)) as vagas_disponiveis from evento LEFT JOIN evento_r_usuario AS inscricoes ON evento.evento_id=inscricoes.evento_id AND inscricoes.status NOT IN ('cancelado') where evento.evento_id=?;";
//
//        // Usa queryForObject para retornar um único valor
//        Integer vagasDisponiveis = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
//
//        // Verifica se vagasDisponiveis é null e retorna 0 neste caso, senão retorna o valor de vagasDisponiveis
//        return vagasDisponiveis != null ? vagasDisponiveis : 0;
//    }
//
//    @Override
//    public int inserirVisita(int id) {
//        return jdbcTemplate.update("UPDATE evento SET evento_visitas=evento_visitas+1 WHERE evento_Id=?", id);
//    }
}
