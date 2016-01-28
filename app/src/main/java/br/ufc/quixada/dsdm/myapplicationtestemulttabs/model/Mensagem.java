package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.User;

/**
 * Created by lucas on 27/01/16.
 */
public class Mensagem {

    private  String idFrom;
    private String idTo;
    private String message;
    private double latitude;
    private double longitude;

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
