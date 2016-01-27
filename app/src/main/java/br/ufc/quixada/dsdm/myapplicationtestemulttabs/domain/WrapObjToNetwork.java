package br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain;

import java.util.LinkedList;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Usuario;

/**
 * Created by viniciusthiengo on 7/26/15.
 */
public class WrapObjToNetwork {
    private Usuario usuarioRemetente;
    private Usuario usuarioDestinatario;
    private Message message;
    private LinkedList<Message> messages;
    private String method;


    public WrapObjToNetwork(LinkedList<Message> messages, String method) {
        this.messages = messages;
        this.method = method;
    }
    public WrapObjToNetwork(Usuario usuarioRemetente, Usuario usuarioDestinatario, String method) {
        this.usuarioRemetente = usuarioRemetente;
        this.usuarioDestinatario = usuarioDestinatario;
        this.method = method;
    }
    public WrapObjToNetwork(Usuario usuario) {
        this.usuarioRemetente = usuario;
    }
    public WrapObjToNetwork(Message message, String method) {
        this.message = message;
        this.method = method;
    }

    public Usuario getUsuarioRemetente() {

        return usuarioRemetente;
    }

    public void setUsuarioRemetente(Usuario usuarioRemetente) {
        this.usuarioRemetente = usuarioRemetente;
    }

    public Usuario getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }

    public Usuario getUsuario() {
        return usuarioRemetente;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioRemetente = usuario;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }
}
