package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

/**
 * Created by lucas on 27/01/16.
 */
public class Amigo {
    private int id;
    private int idAmigo;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Amigo(){}

    public int getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
