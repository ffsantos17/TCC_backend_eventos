package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.DocumentosEvento;
import br.com.projeto.api_projeto.models.Evento;
import br.com.projeto.api_projeto.models.EventosUsuario;
import br.com.projeto.api_projeto.models.ParticipanteEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DBEventoRepository  implements EventoRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DocumentoRepository documentoRepository;


    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


    @Override
    public int salvar(Evento evento, ArrayList<String> documentos) {
        String SQL = "INSERT INTO `evento`(`evento_link`, `evento_data`, `evento_dataFim`, `evento_data_criacao`, `evento_id_usuario_criacao`, `evento_linkepublico`, `evento_nome`, `evento_vagas`, `evento_imagem`, `evento_descricao`, `evento_local`) VALUES (?,?,?,NOW(),?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, evento.getLink());
            ps.setString(2, simpleDateFormat.format(evento.getData()));
            ps.setString(3, simpleDateFormat.format(evento.getDataFim()));
            ps.setInt(4, evento.getIdUsuarioCriacao());
            ps.setBoolean(5, evento.isLinkEPublico());
            ps.setString(6, evento.getNome());
            ps.setInt(7, evento.getVagas());
            ps.setString(8, evento.getImagem());
            ps.setString(9, evento.getDescricao());
            ps.setString(10, evento.getLocal());

            return ps;
        }, keyHolder);

        int linhaInserida = keyHolder.getKey().intValue();

        documentos.forEach(e ->{
            jdbcTemplate.update("INSERT INTO `evento_r_documento`(`evento_id`, `documento_id`) VALUES (?,?)", linhaInserida, e);
        });

        return linhaInserida;
//        return jdbcTemplate.update("INSERT INTO `evento`(`evento_link`, `evento_data`, `evento_dataFim`, `evento_data_criacao`, `evento_id_usuario_criacao`, `evento_linkepublico`, `evento_nome`, `evento_vagas`, `evento_imagem`, `evento_descricao`, `evento_local`) VALUES (?,?,?,NOW(),?,?,?,?,?,?,?)",
//                new Object[] { evento.getData(), evento.getLink(), evento.getDataCriacao(), evento.getIdUsuarioCriacao(), evento.isLinkEPublico(), evento.getNome(), evento.getVagas() });
    }

    @Override
    public int atualizar(Evento evento) {
        return jdbcTemplate.update("UPDATE evento SET evento_link=?,evento_data=?,evento_linkepublico=?,evento_nome=?,evento_vagas=? WHERE evento_Id=?",
                new Object[] {evento.getLink(), evento.getData(), evento.isLinkEPublico(), evento.getNome(), evento.getVagas(), evento.getId() });
    }

    @Override
    public Evento buscarPorId(int id) {
        try {
            Evento evento = jdbcTemplate.queryForObject("SELECT evento.evento_id as id, evento_link as link, evento_data as data, evento_DataFim as dataFim, evento_data_criacao as dataCriacao, evento_id_usuario_criacao as idUsuarioCriacao, evento_linkepublico as linkEPublico, evento_nome as nome, evento_vagas as vagas, evento_imagem as imagem, evento_descricao as descricao, evento_visitas as visitas, evento_local as local, (evento.evento_vagas-COUNT(inscricoes.evento_r_usuario_id)) as vagasDisponiveis FROM evento LEFT JOIN evento_r_usuario AS inscricoes ON evento.evento_id=inscricoes.evento_id AND inscricoes.lista_item_tipoInscricao_id = 3 AND inscricoes.lista_item_statusInscricao_id NOT IN (6) WHERE evento.evento_id=?",
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
        return jdbcTemplate.query("SELECT evento.evento_id as id, evento_link as link, evento_data as data, evento_DataFim as dataFim, evento_data_criacao as dataCriacao, evento_id_usuario_criacao as idUsuarioCriacao, evento_linkepublico as linkEPublico, evento_nome as nome, evento_vagas as vagas, evento_imagem as imagem, evento_descricao as descricao, evento_visitas as visitas, evento_local as local, (evento.evento_vagas-COUNT(inscricoes.evento_r_usuario_id)) as vagasDisponiveis from evento LEFT JOIN evento_r_usuario AS inscricoes ON evento.evento_id=inscricoes.evento_id AND inscricoes.lista_item_tipoInscricao_id = 3 AND inscricoes.lista_item_statusInscricao_id NOT IN (6) where evento_data > NOW() GROUP BY evento.evento_id, evento_link, evento_data, evento_data_criacao, evento_id_usuario_criacao, evento_linkepublico, evento_nome, evento_vagas, evento_imagem ORDER BY evento_data ASC;", BeanPropertyRowMapper.newInstance(Evento.class));

    }

    @Override
    public int alterarStatusInscricao(int id, int status_id){
        return jdbcTemplate.update("UPDATE `evento_r_usuario` SET `lista_item_statusInscricao_id`=? WHERE evento_r_usuario_id=?", status_id, id);
    }

    @Override
    public int buscarVagasEvento(int id) {
        String sql = "SELECT (evento.evento_vagas-COUNT(inscricoes.evento_r_usuario_id)) as vagas_disponiveis from evento LEFT JOIN evento_r_usuario AS inscricoes ON evento.evento_id=inscricoes.evento_id AND inscricoes.lista_item_tipoInscricao_id = 3 AND inscricoes.lista_item_statusInscricao_id NOT IN (6) where evento.evento_id=?;";

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

    @Override
    public List<ParticipanteEvento> buscarParticipanteEvento(int idEvento) {
        try {
            List<ParticipanteEvento> participantes = jdbcTemplate.query("SELECT part.evento_r_usuario_id as id,\n" +
                            "part.evento_id as idEvento,\n" +
                            "part.usuario_id as idUsuario, \n" +
                            "part.lista_item_tipoInscricao_id as tipoParticipante_Id, \n" +
                            "l.lista_item_nome as tipoParticipante_Nome, \n" +
                            "part.lista_item_statusInscricao_id as status_id,\n" +
                            "listaStatus.lista_item_nome as status, \n" +
                            "(SELECT COUNT(*) FROM usuario_r_documento WHERE evento_r_usuario_id = part.evento_r_usuario_id AND entregue = 1) as documentosEntregues, \n" +
                            "(SELECT COUNT(*) FROM usuario_r_documento WHERE evento_r_usuario_id = part.evento_r_usuario_id AND anexo_visualizado=false) as documentosSemVisualizar, \n" +
                            "(SELECT COUNT(*) FROM usuario_r_documento WHERE evento_r_usuario_id = part.evento_r_usuario_id) as totalDocumentos \n" +
                            "FROM evento_r_usuario as part \n" +
                            "LEFT JOIN lista_item as l ON part.lista_item_tipoInscricao_id = l.lista_item_id AND l.lista_id=1\n" +
                            "LEFT JOIN lista_item as listaStatus ON listaStatus.lista_item_id=part.lista_item_statusInscricao_id AND listaStatus.lista_id=2\n" +
                            "WHERE part.evento_id = ?",
                    BeanPropertyRowMapper.newInstance(ParticipanteEvento.class), idEvento);
            return participantes;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
