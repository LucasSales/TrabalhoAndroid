package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

/**
 * Created by lucas on 09/02/16.
 */
public class MensagemLocal {
    private String mensagem;
    private String nomeAmigo;
    private int enviadoPor;
    private int idAmigo;

    public int getEnviadoPor() {
        return enviadoPor;
    }

    public void setEnviadoPor(int enviadoPor) {
        this.enviadoPor = enviadoPor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeAmigo() {
        return nomeAmigo;
    }

    public void setNomeAmigo(String nomeAmigo) {
        this.nomeAmigo = nomeAmigo;
    }

    public int getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }

    @Override
    public String toString() {
        return "Msg "+mensagem+" nomeAntigo "+nomeAmigo+" enviaadoPor "+enviadoPor+" idAmigo "+idAmigo;
    }
}
