package br.com.projeto.api_projeto.models;

import java.util.List;

public class EventosUsuario {

    private int id;
    private int idUsuario;
    private int idEvento;
    private String status;
    private Evento evento;
    private List<DocumentoUsuario> documentos;

    public EventosUsuario() {}

    public EventosUsuario(int id, int idUsuario, int idEvento) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
    }

    public EventosUsuario(int id, int idUsuario, int idEvento, String status,Evento evento) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
        this.status = status;
        this.evento = evento;
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
}
