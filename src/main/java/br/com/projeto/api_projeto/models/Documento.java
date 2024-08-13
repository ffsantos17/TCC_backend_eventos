package br.com.projeto.api_projeto.models;

import java.util.Date;

public class Documento{


    private int id;
    private String nome;
    private boolean possuiModelo;
    private String modelo;
    private Date dataCriacao;
    private boolean excluido;

    public Documento() {}

    public Documento(int id, String nome, boolean possuiModelo, String modelo, Date dataCriacao, boolean excluido) {
        this.id = id;
        this.nome = nome;
        this.possuiModelo = possuiModelo;
        this.modelo = modelo;
        this.dataCriacao = dataCriacao;
        this.excluido = excluido;
    }

    public Documento(int id, String nome, boolean possuiModelo, String modelo, Date dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.possuiModelo = possuiModelo;
        this.modelo = modelo;
        this.dataCriacao = dataCriacao;
    }

    public Documento(String nome, boolean possuiModelo, String modelo) {
        this.nome = nome;
        this.possuiModelo = possuiModelo;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPossuiModelo() {
        return possuiModelo;
    }

    public void setPossuiModelo(boolean possuiModelo) {
        this.possuiModelo = possuiModelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }
}
