package br.com.projeto.api_projeto.models;

import java.util.Date;

public class Alerta {

    private int id;
    private String tipoAlerta_id;
    private String tipoAlerta_nome;
    private String descricao;
    private String usuarioCriacao_id;
    private String usuarioCriacao_nome;
    private Date data;
    private boolean lido;

    public Alerta() {}

    public Alerta(int id, String tipoAlerta_id, String tipoAlerta_nome, String descricao, String usuarioCriacao_id, String usuarioCriacao_nome, Date data, boolean lido) {
        this.id = id;
        this.tipoAlerta_id = tipoAlerta_id;
        this.tipoAlerta_nome = tipoAlerta_nome;
        this.descricao = descricao;
        this.usuarioCriacao_id = usuarioCriacao_id;
        this.usuarioCriacao_nome = usuarioCriacao_nome;
        this.data = data;
        this.lido = lido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoAlerta_id() {
        return tipoAlerta_id;
    }

    public void setTipoAlerta_id(String tipoAlerta_id) {
        this.tipoAlerta_id = tipoAlerta_id;
    }

    public String getTipoAlerta_nome() {
        return tipoAlerta_nome;
    }

    public void setTipoAlerta_nome(String tipoAlerta_nome) {
        this.tipoAlerta_nome = tipoAlerta_nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUsuarioCriacao_id() {
        return usuarioCriacao_id;
    }

    public void setUsuarioCriacao_id(String usuarioCriacao_id) {
        this.usuarioCriacao_id = usuarioCriacao_id;
    }

    public String getUsuarioCriacao_nome() {
        return usuarioCriacao_nome;
    }

    public void setUsuarioCriacao_nome(String usuarioCriacao_nome) {
        this.usuarioCriacao_nome = usuarioCriacao_nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isLido() {
        return lido;
    }

    public void setLido(boolean lido) {
        this.lido = lido;
    }
}

