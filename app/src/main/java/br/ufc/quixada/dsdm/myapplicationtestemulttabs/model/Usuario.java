package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.NotificationConf;

/**
 * Created by lucas on 27/01/16.
 */
public class Usuario {
    private String registrationId;
    private String urlFoto;
    private long id;
    private String nickname;

    public Usuario(){

    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Usuario(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
