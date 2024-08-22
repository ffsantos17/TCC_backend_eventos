package br.com.projeto.api_projeto.repositories;

import br.com.projeto.api_projeto.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class DBUsuarioRepository implements UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    AlertaRepository alertaRepository;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public int salvar(Usuario usuario) {
//        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        return jdbcTemplate.update("INSERT INTO usuario(usuario_cpf, usuario_email, usuario_matricula, usuario_nome, usuario_Senha, tipo_usuario_id) VALUES (?,?,?,?,?,?)",
                new Object[] { usuario.getCpf(), usuario.getEmail(), usuario.getMatricula(), usuario.getNome(), usuario.getSenha(), usuario.getTipoUsuarioId()});
    }

    @Override
    public int atualizar(Usuario usuario) {
        return jdbcTemplate.update("UPDATE usuario SET usuario_cpf=?,usuario_email=?,usuario_matricula=?,usuario_nome=?,usuario_Senha=?,tipo_usuario_id=? WHERE usuario_id = ?",
                new Object[] { usuario.getCpf(), usuario.getEmail(), usuario.getMatricula(), usuario.getNome(), usuario.getSenha(), usuario.getTipoUsuarioId(), usuario.getId()});
    }

    @Override
    public Usuario buscarPorId(int id) {
        try {
            Usuario usuario = jdbcTemplate.queryForObject("SELECT usuario_id as id, usuario_cpf as cpf, usuario_email as email, usuario_matricula as matricula, usuario_nome as nome, usuario_Senha as senha, usuario.tipo_usuario_id as tipoUsuarioId, tipo_usuario.tipo_usuario_Nome as tipoUsuarioNome FROM usuario LEFT JOIN tipo_usuario ON tipo_usuario.tipo_usuario_id=usuario.tipo_usuario_id WHERE usuario_id=?",
                    BeanPropertyRowMapper.newInstance(Usuario.class), id);
            return usuario;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deletarPorId(int id) {
        return jdbcTemplate.update("DELETE FROM usuario WHERE usuario_id=?", id);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return jdbcTemplate.query("SELECT usuario_id as id, usuario_cpf as cpf, usuario_email as email, usuario_matricula as matricula, usuario_nome as nome, usuario_Senha as senha, usuario.tipo_usuario_id as tipoUsuarioId, tipo_usuario.tipo_usuario_Nome as tipoUsuarioNome FROM usuario LEFT JOIN tipo_usuario ON tipo_usuario.tipo_usuario_id=usuario.tipo_usuario_id", BeanPropertyRowMapper.newInstance(Usuario.class));
    }

    public Usuario buscarPorEmail(String email) {
        try {
            Usuario usuario = jdbcTemplate.queryForObject("SELECT usuario_id as id, usuario_cpf as cpf, usuario_email as email, usuario_matricula as matricula, usuario_nome as nome, usuario_Senha as senha, usuario.tipo_usuario_id as tipoUsuarioId, tipo_usuario.tipo_usuario_Nome as tipoUsuarioNome FROM usuario LEFT JOIN tipo_usuario ON tipo_usuario.tipo_usuario_id=usuario.tipo_usuario_id WHERE usuario_email like ?",
                    BeanPropertyRowMapper.newInstance(Usuario.class), email);
            return usuario;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public Usuario buscarUsuario(String email, String cpf, String matricula) {
        try {
            Usuario usuario = jdbcTemplate.queryForObject("SELECT usuario_id as id, usuario_cpf as cpf, usuario_email as email, usuario_matricula as matricula, usuario_nome as nome, usuario_Senha as senha, usuario.tipo_usuario_id as tipoUsuarioId, tipo_usuario.tipo_usuario_Nome as tipoUsuarioNome FROM usuario LEFT JOIN tipo_usuario ON tipo_usuario.tipo_usuario_id=usuario.tipo_usuario_id WHERE usuario_email like ? or usuario_cpf like ? or usuario_matricula like ?",
                    BeanPropertyRowMapper.newInstance(Usuario.class), email, cpf, matricula);
            return usuario;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public List<EventosUsuario> buscarEventosUsuarios(int idUsuario) {
        try {
            List<EventosUsuario> eventos = jdbcTemplate.query("SELECT evento_r_usuario_id as id, " +
                            "evento_id as idEvento, " +
                            "usuario_id as idUsuario," +
                            "evento_r_usuario.lista_item_statusInscricao_id as status_id, " +
                            "listaStatus.lista_item_nome as status, " +
                            "lista_item_tipoInscricao_id as tipoInscricao_Id, " +
                            "listaI.lista_item_nome as tipoInscricao_Nome " +
                            "FROM `evento_r_usuario` \n" +
                            "LEFT JOIN lista_item AS listaI ON listaI.lista_item_id=evento_r_usuario.lista_item_tipoInscricao_id AND listaI.lista_id=1 \n" +
                            "LEFT JOIN lista_item AS listaStatus ON listaStatus.lista_item_id=evento_r_usuario.lista_item_statusInscricao_id AND listaStatus.lista_id=2 \n" +
                            "WHERE usuario_id=?",
                    BeanPropertyRowMapper.newInstance(EventosUsuario.class), idUsuario);
            eventos.forEach(e ->{
                e.setEvento(eventoRepository.buscarPorId(e.getIdEvento()));
                e.setDocumentos(buscarDocumentosUsuarios(e.getId()));
            });

            return eventos;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    public EventosUsuario buscarEventoUsuario(int idEventoUsuario, int usuarioId) {
        try {
            EventosUsuario evento = jdbcTemplate.queryForObject("SELECT evento_r_usuario_id as id, evento_id as idEvento, usuario_id as idUsuario, evento_r_usuario.lista_item_statusInscricao_id as status_id, listaStatus.lista_item_nome as status FROM `evento_r_usuario` LEFT JOIN lista_item AS listaStatus ON listaStatus.lista_item_id=evento_r_usuario.lista_item_statusInscricao_id AND listaStatus.lista_id=2 WHERE evento_r_usuario_id=? and usuario_id = ?",
                    BeanPropertyRowMapper.newInstance(EventosUsuario.class), idEventoUsuario, usuarioId);
            if(evento != null) {
                evento.setEvento(eventoRepository.buscarPorId(evento.getIdEvento()));
                evento.setDocumentos(buscarDocumentosUsuarios(evento.getId()));
            }

            return evento;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public List<DocumentoUsuario> buscarDocumentosUsuarios(int idEventoUsuario) {
        try {
            List<DocumentoUsuario> documentos = jdbcTemplate.query("SELECT userDoc.id, userDoc.evento_r_usuario_id as idEventoUsuario, doc.documento_id as idDocumento, userDoc.entregue as entregue, anexo_Nome as nomeAnexo, userDoc.anexo_visualizado as visualizado FROM `usuario_r_documento` as userDoc LEFT JOIN evento_r_documento as eveDoc ON eveDoc.evento_r_documento_id=userDoc.evento_r_documento_id LEFT JOIN documento as doc ON doc.documento_id=eveDoc.documento_id WHERE evento_r_usuario_id=?;",
                    BeanPropertyRowMapper.newInstance(DocumentoUsuario.class), idEventoUsuario);
            documentos.forEach(d ->{
                d.setDocumento(documentoRepository.buscarPorId(d.getIdDocumento()));
                d.setAlertas(alertaRepository.listar(d.getId(), "usuario_r_documento"));
            });


            return documentos;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }

    }

    public boolean validaEmailSenha(Usuario usuarioRequest) {
        Usuario usuario = buscarPorEmail(usuarioRequest.getEmail());
        String senhaRequest = usuarioRequest.getSenha();
        if(usuario.getSenha().equals(senhaRequest)) {
            return true;
        }else {
            return false;
        }
    }

    public int inscreverEvento(int eventoId, int usuarioId){
//        var response = jdbcTemplate.update("INSERT INTO evento_r_usuario(evento_id, usuario_id, status) VALUES (?, ?,'pendente')", eventoId, usuarioId);
        String SQL = "INSERT INTO evento_r_usuario(evento_id, usuario_id, lista_item_statusInscricao_id) VALUES (?, ?,5)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, eventoId);
            ps.setInt(2, usuarioId);
            return ps;
        }, keyHolder);

        int linhaInserida = keyHolder.getKey().intValue();

        String SQL_usuarioDoc = "INSERT INTO usuario_r_documento(evento_r_usuario_id, evento_r_documento_id, entregue) VALUES (?,?,0)";

        List<DocumentosEvento> documentos = eventoRepository.buscarDocumentoEvento(eventoId);

        documentos.forEach(e ->{
            jdbcTemplate.update("INSERT INTO usuario_r_documento(evento_r_usuario_id, evento_r_documento_id, entregue) VALUES (?,?,0)", linhaInserida, e.getId());
        });
        return linhaInserida;
    }
}
