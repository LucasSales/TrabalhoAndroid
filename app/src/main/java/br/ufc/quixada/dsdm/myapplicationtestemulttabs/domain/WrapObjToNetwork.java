package br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain;

import java.util.LinkedList;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Usuario;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.VerificaMensagem;

/**
 * Created by viniciusthiengo on 7/26/15.
 */
public class WrapObjToNetwork {
    private Usuario usuarioRemetente;
    private Usuario usuarioDestinatario;
    private VerificaMensagem temMensagem;
    private Amigo amigo;
    private Mensagem message;
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
    public WrapObjToNetwork(Mensagem message) {
        this.message = message;
    }
    public WrapObjToNetwork(Amigo amigo) {
        this.amigo = amigo;
    }

    public WrapObjToNetwork(VerificaMensagem temMensagem){
        this.temMensagem = temMensagem;
    }

    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
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

    public Mensagem getMessage() {
        return message;
    }

    public void setMessage(Mensagem message) {
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
