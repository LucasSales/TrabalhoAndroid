package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.controle.BancoDeDados;

/**
 * Created by lucas on 27/01/16.
 */
public class AmigoDAO {
    private SQLiteDatabase db;

    public AmigoDAO(Context context){
        BancoDeDados aux = new BancoDeDados(context);
        db = aux.getWritableDatabase();
    }

    public void inserir(Amigo amigo){
        ContentValues valores = new ContentValues();
        valores.put("nome",amigo.getNickname());
        valores.put("_id",amigo.getIdAmigo());

        db.insert("amigos",null,valores);
    }

    public void deletar(Amigo amigo){
        db.delete("amigos", "_id=" + amigo.getIdAmigo(), null);
    }

    public void atualizar(Amigo amigo){
        ContentValues valores = new ContentValues();
        valores.put("nome",amigo.getNickname());

        db.update("amigo", valores, "_id=?", new String[]{"" + amigo.getIdAmigo()});
    }
}
