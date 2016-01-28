package br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 8/10/15.
 */
public class User{
    public int id;
    public String registro_id;
    public String email;
    public String senha;
    public String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistro_id() {
        return registro_id;
    }

    public void setRegistro_id(String registro_id) {
        this.registro_id = registro_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
