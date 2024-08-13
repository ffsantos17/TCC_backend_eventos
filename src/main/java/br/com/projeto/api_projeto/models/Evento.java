package br.com.projeto.api_projeto.models;

import java.util.Date;
import java.util.List;

public class Evento {


    private int id;
    private String nome;
    private Date data;
    private Date dataFim;
    private int idUsuarioCriacao;
    private Date dataCriacao;
    private boolean linkEPublico;
    private String link;
    private int vagas;
    private String imagem;
    private String descricao;
    private int visitas;
    private String local;
    private int vagasDisponiveis;
    private List<DocumentosEvento> documentos;

    public Evento() {}

    public Evento(int id, String nome, Date data, Date dataFim, int idUsuarioCriacao, Date dataCriacao, boolean linkEPublico, String link, int vagas, String imagem, String descricao, int visitas, String local, int vagasDisponiveis, List<DocumentosEvento> documentos) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.dataFim = dataFim;
        this.idUsuarioCriacao = idUsuarioCriacao;
        this.dataCriacao = dataCriacao;
        this.linkEPublico = linkEPublico;
        this.link = link;
        this.vagas = vagas;
        this.imagem = imagem;
        this.descricao = descricao;
        this.visitas = visitas;
        this.local = local;
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public Evento(int vagas, String link, boolean linkEPublico, Date dataCriacao, int idUsuarioCriacao, Date data, Date dataFim, String nome) {
        this.vagas = vagas;
        this.link = link;
        this.linkEPublico = linkEPublico;
        this.dataCriacao = dataCriacao;
        this.idUsuarioCriacao = idUsuarioCriacao;
        this.data = data;
        this.dataFim = dataFim;
        this.nome = nome;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdUsuarioCriacao() {
        return idUsuarioCriacao;
    }

    public void setIdUsuarioCriacao(int idUsuarioCriacao) {
        this.idUsuarioCriacao = idUsuarioCriacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isLinkEPublico() {
        return linkEPublico;
    }

    public void setLinkEPublico(boolean linkEPublico) {
        this.linkEPublico = linkEPublico;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<DocumentosEvento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentosEvento> documentos) {
        this.documentos = documentos;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
