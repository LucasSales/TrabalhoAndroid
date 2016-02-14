package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.controle.BancoDeDados;

/**
 * Created by lucas on 09/02/16.
 */
public class MensagemLocalDAO {
    private SQLiteDatabase db;

    public MensagemLocalDAO(Context context){
        BancoDeDados aux = new BancoDeDados(context);
        db = aux.getWritableDatabase();
    }

    public void inserir(MensagemLocal mensagem){
        ContentValues valores = new ContentValues();
        valores.put("nomeAmigo", mensagem.getNomeAmigo());
        valores.put("texto", mensagem.getMensagem());
        valores.put("_idAmigo",mensagem.getIdAmigo());
        valores.put("enviadoPor",mensagem.getEnviadoPor());

        db.insert("mensagem",null,valores);
    }

    public void deletar(MensagemLocal mensagem){
        db.delete("mensagem", "_idAmigo=" + mensagem.getIdAmigo(), null);
    }

    /*public void atualizar(Amigo amigo){
        ContentValues valores = new ContentValues();
        valores.put("nick",amigo.getNick());

        db.update("amigos", valores, "_id=?", new String[]{"" + amigo.getId()});
    }*/
    public List<MensagemLocal> buscar(){
        List<MensagemLocal> listaMensagens = new ArrayList<>();
        String[] colunas = new String[]{"_idAmigo","texto","nomeAmigo","enviadoPor"};
        Cursor cursor = db.query("mensagem",colunas,null,null,null,null,null);




        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                MensagemLocal msg = new MensagemLocal();

                msg.setIdAmigo(cursor.getInt(0));
                msg.setMensagem(cursor.getString(1));
                msg.setNomeAmigo(cursor.getString(2));
                msg.setEnviadoPor(cursor.getInt(3));

                listaMensagens.add(msg);
            }while(cursor.moveToNext());
        }


        return listaMensagens;
    }

    public List<MensagemLocal> buscarPorID(Integer id){
        List<MensagemLocal> listaMensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagem WHERE _idAmigo="+id;
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                MensagemLocal msg = new MensagemLocal();

                msg.setIdAmigo(cursor.getInt(0));
                msg.setMensagem(cursor.getString(1));
                msg.setNomeAmigo(cursor.getString(2));
                msg.setEnviadoPor(cursor.getInt(3));

                listaMensagens.add(msg);
            }while(cursor.moveToNext());
        }
        return listaMensagens;
    }

}
