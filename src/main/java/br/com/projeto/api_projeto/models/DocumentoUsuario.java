package br.com.projeto.api_projeto.models;

import java.util.ArrayList;
import java.util.List;

public class DocumentoUsuario {

    private int id;
    private int idEventoUsuario;
    private int idDocumento;
    private Documento documento;
    private boolean entregue;
    private String nomeAnexo;
    private boolean visualizado;
    private List<Alerta> alertas;

    public DocumentoUsuario() {}

    public DocumentoUsuario(int id, int idEventoUsuario, int idDocumento, boolean entregue, String nomeAnexo) {
        this.id = id;
        this.idEventoUsuario = idEventoUsuario;
        this.idDocumento = idDocumento;
        this.entregue = entregue;
        this.nomeAnexo = nomeAnexo;
    }

    public DocumentoUsuario(int id, int idEventoUsuario, int idDocumento, boolean entregue, Documento documento, String nomeAnexo, List<Alerta> alertas, boolean visualizado) {
        this.id = id;
        this.idEventoUsuario = idEventoUsuario;
        this.idDocumento = idDocumento;
        this.entregue = entregue;
        this.documento = documento;
        this.nomeAnexo = nomeAnexo;
        this.alertas = alertas;
        this.visualizado = visualizado;
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

    public String getNomeAnexo() {
        return nomeAnexo;
    }

    public void setNomeAnexo(String nomeAnexo) {
        this.nomeAnexo = nomeAnexo;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    public boolean isVisualizado() {
        return visualizado;
    }

    public void setVisualizado(boolean visualizado) {
        this.visualizado = visualizado;
    }
}
