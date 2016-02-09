package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        //valores.put("_id",mensagem.getId());

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
    public List<MensagemLocal> buscar(Integer id){
        List<MensagemLocal> listaMensagens = new ArrayList<>();
        String[] colunas = new String[]{"_idAmigo","texto","nomeAmigo"};
        Cursor cursor = db.query("mensagem",colunas,null,null,null,null,null);

        //Cursor cursor = db.rawQuery("select * from mensagem where idAmigo = ?",new String[]{"" + id});
        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                MensagemLocal msg = new MensagemLocal();

                msg.setIdAmigo(cursor.getInt(0));
                msg.setMensagem(cursor.getString(1));
                msg.setNomeAmigo(cursor.getString(2));


                listaMensagens.add(msg);
            }while(cursor.moveToNext());
        }


        return listaMensagens;
    }
}
