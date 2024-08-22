package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.Alerta;
import br.com.projeto.api_projeto.models.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DBAlertaRepository implements AlertaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Alerta> listar(int registroId, String tabela) {
        try {
            List<Alerta> alertas = jdbcTemplate.query("SELECT \n" +
                    "alerta_id as id,\n" +
                    "lista_item_tipoAlerta_id as tipoAlerta_id,\n" +
                    "lista_item.lista_item_nome as tipoAlerta_nome,\n" +
                    "alerta_descricao as descricao,\n" +
                    "alerta_usuarioCriacao_id as usuarioCriacao_id,\n" +
                    "usuario.usuario_nome as usuarioCriacao_nome,\n" +
                    "alerta_dataCriacao as data,\n" +
                    "alerta_lido as lido\n" +
                    "FROM alerta \n" +
                    "LEFT JOIN usuario ON usuario.usuario_id=alerta.alerta_usuarioCriacao_id\n" +
                    "LEFT JOIN lista_item ON alerta.lista_item_tipoAlerta_id=lista_item.lista_item_id AND lista_item.lista_id=3\n" +
                    "WHERE alerta_tabelaRegistro_id=? AND alerta_tabela=?\n" +
                    "ORDER BY `alerta_dataCriacao` DESC;", BeanPropertyRowMapper.newInstance(Alerta.class), registroId, tabela);
            return alertas;
        }catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public int criar(Alerta alerta, int registro_id, String tabela) {
        return jdbcTemplate.update("INSERT INTO `alerta`(`lista_item_tipoAlerta_id`,\n" +
                                        "`alerta_descricao`, \n" +
                                        "`alerta_usuarioCriacao_id`, \n" +
                                        "`alerta_dataCriacao`, \n" +
                                        "`alerta_tabela`, \n" +
                                        "`alerta_tabelaRegistro_id`,\n" +
                                        "`alerta_lido`) \n" +
                                        "VALUES (?,?,?,NOW(),?,?,false)",
                alerta.getTipoAlerta_id(), alerta.getDescricao(), alerta.getUsuarioCriacao_id(), tabela, registro_id);
    }

    @Override
    public int lerAlerta(int alerta_id) {
        return jdbcTemplate.update("UPDATE `alerta` SET `alerta_lido`=1 WHERE `alerta_id`=?",
                alerta_id);
    }
}
