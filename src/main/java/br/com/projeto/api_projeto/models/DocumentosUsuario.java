package br.com.projeto.api_projeto.models;

import javax.print.Doc;

public class DocumentosUsuario {

    private int id;
    private int idEventoUsuario;
    private int idDocumento;
    private Documento documento;
    private boolean entregue;

    public DocumentosUsuario() {}

    public DocumentosUsuario(int id, int idEventoUsuario, int idDocumento, boolean entregue) {
        this.id = id;
        this.idEventoUsuario = idEventoUsuario;
        this.idDocumento = idDocumento;
        this.entregue = entregue;
    }

    public DocumentosUsuario(int id, int idEventoUsuario, int idDocumento, boolean entregue, Documento documento) {
        this.id = id;
        this.idEventoUsuario = idEventoUsuario;
        this.idDocumento = idDocumento;
        this.entregue = entregue;
        this.documento = documento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEventoUsuario() {
        return idEventoUsuario;
    }

    public void setIdEventoUsuario(int idEventoUsuario) {
        this.idEventoUsuario = idEventoUsuario;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }
}
