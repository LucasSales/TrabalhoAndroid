package br.ufc.quixada.dsdm.myapplicationtestemulttabs.Model;

/**
 * Created by Ronson Cavalcante on 20/01/2016.
 */
public class Mensagem_Grupo {

    private String Local;
    private String NomeGrupo;
    private Long idMensagem;

    public Mensagem_Grupo(Long idMensagem, String local, String nomeGrupo) {
        this.idMensagem = idMensagem;
        this.Local = local;
        this.NomeGrupo = nomeGrupo;
    }

    public Long getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getNomeGrupo() {
        return NomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        NomeGrupo = nomeGrupo;
    }
}
