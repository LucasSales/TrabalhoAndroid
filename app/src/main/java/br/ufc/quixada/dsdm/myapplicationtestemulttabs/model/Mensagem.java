package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.User;

/**
 * Created by lucas on 27/01/16.
 */
public class Mensagem {

    private Usuario userFrom;
    private Usuario userTo;
    private String message;
    private double latitude;
    private double longitude;

    public Usuario getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Usuario userFrom) {
        this.userFrom = userFrom;
    }

    public Usuario getUserTo() {
        return userTo;
    }

    public void setUserTo(Usuario userTo) {
        this.userTo = userTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
