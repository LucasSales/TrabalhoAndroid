package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.User;

/**
 * Created by lucas on 27/01/16.
 */
public class Mensagem {

    private String idFrom;
    private String idTo;
    private String message;
    private int id_loc;
    private Local local;

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(String idFrom) {
        this.idFrom = idFrom;
    }

    public String getIdTo() {
        return idTo;
    }

    public void setIdTo(String idTo) {
        this.idTo = idTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId_loc() {
        return id_loc;
    }

    public void setId_loc(int id_loc) {
        this.id_loc = id_loc;
    }
}
