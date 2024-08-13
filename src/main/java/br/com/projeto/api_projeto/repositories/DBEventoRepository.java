package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.DocumentosEvento;
import br.com.projeto.api_projeto.models.Evento;
import br.com.projeto.api_projeto.models.EventosUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBEventoRepository  implements EventoRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DocumentoRepository documentoRepository;


    @Override
    public int salvar(Evento evento) {
        return jdbcTemplate.update("INSERT INTO evento (evento_data, evento_link, evento_data_criacao, evento_id_usuario_criacao, evento_linkepublico, evento_nome, evento_vagas) VALUES(?,?,?,?,?,?,?)",
                new Object[] { evento.getData(), evento.getLink(), evento.getDataCriacao(), evento.getIdUsuarioCriacao(), evento.isLinkEPublico(), evento.getNome(), evento.getVagas() });
    }

    @Override
    public int atualizar(Evento evento) {
        return jdbcTemplate.update("UPDATE evento SET evento_link=?,evento_data=?,evento_linkepublico=?,evento_nome=?,evento_vagas=? WHERE evento_Id=?",
                new Object[] {evento.getLink(), evento.getData(), evento.isLinkEPublico(), evento.getNome(), evento.getVagas(), evento.getId() });
    }

    @Override
    public Evento buscarPorId(int id) {
        try {
            Evento evento = jdbcTemplate.queryForObject("SELECT evento.evento_id as id, evento_link as link, evento_data as data, evento_DataFim as dataFim, evento_data_criacao as dataCriacao, evento_id_usuario_criacao as idUsuarioCriacao, evento_linkepublico as linkEPublico, evento_nome as nome, evento_vagas as vagas, evento_imagem as imagem, evento_descricao as descricao, evento_visitas as visitas, evento_local as local, (evento.evento_vagas-COUNT(inscricoes.evento_r_usuario_id)) as vagasDisponiveis FROM evento LEFT JOIN evento_r_usuario AS inscricoes ON evento.evento_id=inscricoes.evento_id AND inscricoes.status NOT IN ('cancelado') WHERE evento.evento_id=?",
                    BeanPropertyRowMapper.newInstance(Evento.class), id);
            evento.setDocumentos(buscarDocumentoEvento(evento.getId()));
            return evento;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deletarPorId(int id) {
        return jdbcTemplate.update("DELETE FROM evento WHERE evento_id=?", id);
    }

    @Override
    public List<Evento> buscarTodos() {
        return jdbcTemplate.query("SELECT evento.evento_id as id, evento_link as link, evento_data as data, evento_DataFim as dataFim, evento_data_criacao as dataCriacao, evento_id_usuario_criacao as idUsuarioCriacao, evento_linkepublico as linkEPublico, evento_nome as nome, evento_vagas as vagas, evento_imagem as imagem, evento_descricao as descricao, evento_visitas as visitas, evento_local as local, (evento.evento_vagas-COUNT(inscricoes.evento_r_usuario_id)) as vagasDisponiveis from evento LEFT JOIN evento_r_usuario AS inscricoes ON evento.evento_id=inscricoes.evento_id AND inscricoes.status NOT IN ('cancelado') where evento_data > NOW() GROUP BY evento.evento_id, evento_link, evento_data, evento_data_criacao, evento_id_usuario_criacao, evento_linkepublico, evento_nome, evento_vagas, evento_imagem ORDER BY evento_data ASC;", BeanPropertyRowMapper.newInstance(Evento.class));

    }

    @Override
    public int buscarVagasEvento(int id) {
        String sql = "SELECT (evento.evento_vagas-COUNT(inscricoes.evento_r_usuario_id)) as vagas_disponiveis from evento LEFT JOIN evento_r_usuario AS inscricoes ON evento.evento_id=inscricoes.evento_id AND inscricoes.status NOT IN ('cancelado') where evento.evento_id=?;";

        // Usa queryForObject para retornar um único valor
        Integer vagasDisponiveis = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);

        // Verifica se vagasDisponiveis é null e retorna 0 neste caso, senão retorna o valor de vagasDisponiveis
        return vagasDisponiveis != null ? vagasDisponiveis : 0;
    }

    @Override
    public int inserirVisita(int id) {
        return jdbcTemplate.update("UPDATE evento SET evento_visitas=evento_visitas+1 WHERE evento_Id=?", id);
    }

    @Override
    public List<DocumentosEvento> buscarDocumentoEvento(int idEvento) {
        try {
            List<DocumentosEvento> documentos = jdbcTemplate.query("SELECT evento_r_documento_id as id, evento_id as idEvento, documento_id as idDocumento FROM evento_r_documento WHERE evento_id=?",
                    BeanPropertyRowMapper.newInstance(DocumentosEvento.class), idEvento);
            documentos.forEach(e ->{
                e.setDocumento(documentoRepository.buscarPorId(e.getIdDocumento()));
            });
            return documentos;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
