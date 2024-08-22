package br.com.projeto.api_projeto.models;

import java.util.List;

public class ParticipanteEvento {

    private int id;
    private int idEvento;
    private int idUsuario;
    private int tipoParticipante_Id;
    private String tipoParticipante_Nome;
    private int status_id;
    private String status;
    private int documentosEntregues;
    private int documentosSemVisualizar;
    private int totalDocumentos;
    private Usuario usuario;

    public ParticipanteEvento() {}

    public ParticipanteEvento(int id, int idEvento, int idUsuario, int tipoParticipante_Id, String tipoParticipante_Nome,int  status_id,String status, int documentosEntregues, int documentosSemVisualizar, int totalDocumentos) {
        this.id = id;
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
        this.tipoParticipante_Id = tipoParticipante_Id;
        this.tipoParticipante_Nome = tipoParticipante_Nome;
        this.status_id = status_id;
        this.status = status;
        this.documentosEntregues = documentosEntregues;
        this.documentosSemVisualizar = documentosSemVisualizar;
        this.totalDocumentos = totalDocumentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTipoParticipante_Id() {
        return tipoParticipante_Id;
    }

    public void setTipoParticipante_Id(int tipoParticipante_Id) {
        this.tipoParticipante_Id = tipoParticipante_Id;
    }

    public String getTipoParticipante_Nome() {
        return tipoParticipante_Nome;
    }

    public void setTipoParticipante_Nome(String tipoParticipante_Nome) {
        this.tipoParticipante_Nome = tipoParticipante_Nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getTotalDocumentos() {
        return totalDocumentos;
    }

    public void setTotalDocumentos(int totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }

    public int getDocumentosEntregues() {
        return documentosEntregues;
    }

    public void setDocumentosEntregues(int documentosEntregues) {
        this.documentosEntregues = documentosEntregues;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getDocumentosSemVisualizar() {
        return documentosSemVisualizar;
    }

    public void setDocumentosSemVisualizar(int documentosSemVisualizar) {
        this.documentosSemVisualizar = documentosSemVisualizar;
    }
}
