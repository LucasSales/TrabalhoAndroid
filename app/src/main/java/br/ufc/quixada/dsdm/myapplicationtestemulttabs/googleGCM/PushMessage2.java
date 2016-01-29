package br.ufc.quixada.dsdm.myapplicationtestemulttabs.googleGCM;

/**
 * Created by lucas on 25/01/16.
 */
public class PushMessage2 {
    private String mensagem;

    public PushMessage2(String teste, String menssagem){
        this.mensagem = menssagem;
    }


    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
