package br.com.projeto.api_projeto.models;

public class DocumentosEvento {

    private int id;
    private int idEvento;
    private int idDocumento;
    private Documento documento;

    public DocumentosEvento() {}

    public DocumentosEvento(int id, int idEvento, int idDocumento) {
        this.id = id;
        this.idEvento = idEvento;
        this.idDocumento = idDocumento;
    }

    public DocumentosEvento(int id, int idEvento, int idDocumento, Documento documento) {
        this.id = id;
        this.idEvento = idEvento;
        this.idDocumento = idDocumento;
        this.documento = documento;
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
}
