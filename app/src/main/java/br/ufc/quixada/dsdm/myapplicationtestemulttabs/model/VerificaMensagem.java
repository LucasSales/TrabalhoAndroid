package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

/**
 * Created by lucas on 29/01/16.
 */
public class VerificaMensagem {
    public String token;
    public Local local;

    public VerificaMensagem(Local local, String token){
        this.local = local;
        this.token = token;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
