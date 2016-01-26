package br.ufc.quixada.dsdm.myapplicationtestemulttabs.googleGCM;

/**
 * Created by lucas on 25/01/16.
 */
public class PushMessage2 {
    private String titulo;
    private String mensagem;

    public PushMessage2(String titulo, String menssagem){
        this.titulo = titulo;
        this.mensagem = menssagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
