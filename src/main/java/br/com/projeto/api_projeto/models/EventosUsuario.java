package br.com.projeto.api_projeto.models;

import java.util.List;

public class EventosUsuario {

    private int id;
    private int idUsuario;
    private int idEvento;
    private int status_id;
    private String status;
    private int tipoInscricao_Id;
    private String tipoInscricao_Nome;
    private Evento evento;
    private List<DocumentoUsuario> documentos;

    public EventosUsuario() {}

    public EventosUsuario(int id, int idUsuario, int idEvento) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
    }

    public EventosUsuario(int id, int idUsuario, int idEvento, int status_id,String status,Evento evento, int tipoInscricao_Id, String tipoInscricao_Nome) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
        this.status_id = status_id;
        this.status = status;
        this.evento = evento;
        this.tipoInscricao_Id = tipoInscricao_Id;
        this.tipoInscricao_Nome = tipoInscricao_Nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<DocumentoUsuario> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoUsuario> documentos) {
        this.documentos = documentos;
    }

    public int getTipoInscricao_Id() {
        return tipoInscricao_Id;
    }

    public void setTipoInscricao_Id(int tipoInscricao_Id) {
        this.tipoInscricao_Id = tipoInscricao_Id;
    }

    public String getTipoInscricao_Nome() {
        return tipoInscricao_Nome;
    }

    public void setTipoInscricao_Nome(String tipoInscricao_Nome) {
        this.tipoInscricao_Nome = tipoInscricao_Nome;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
}
