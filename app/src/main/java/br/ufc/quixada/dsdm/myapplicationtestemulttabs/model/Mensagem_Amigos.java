package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

/**
 * Created by Robson Cavalcante on 10/12/2015.
 */
public class Mensagem_Amigos {

    private String nome_amigo;
    private String texto;
    private String ultimo_texto;
    private String ultima_visualizacao;
    private long id_mensagem;
    private String img_amigo;

    public Mensagem_Amigos(String nome_amigo, String texto, String ultimo_texto, String ultima_visualizacao, long id_mensagem, String img_amigo) {
        this.nome_amigo = nome_amigo;
        this.texto = texto;
        this.ultimo_texto = ultimo_texto;
        this.ultima_visualizacao = ultima_visualizacao;
        this.id_mensagem = id_mensagem;
        this.img_amigo = img_amigo;
    }
    public Mensagem_Amigos(){}

    public String getNome_amigo() {
        return nome_amigo;
    }

    public void setNome_amigo(String nome_amigo) {
        this.nome_amigo = nome_amigo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUltimo_texto() {
        return ultimo_texto;
    }

    public void setUltimo_texto(String ultimo_texto) {
        this.ultimo_texto = ultimo_texto;
    }

    public String getUltima_visualizacao() {
        return ultima_visualizacao;
    }

    public void setUltima_visualizacao(String ultima_visualizacao) {
        this.ultima_visualizacao = ultima_visualizacao;
    }

    public long getId_mensagem() {
        return id_mensagem;
    }

    public void setId_mensagem(long id_mensagem) {
        this.id_mensagem = id_mensagem;
    }

    public String getImg_amigo() {
        return img_amigo;
    }

    public void setImg_amigo(String img_amigo) {
        this.img_amigo = img_amigo;
    }
}
