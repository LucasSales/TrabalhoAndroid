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
        valores.put("nome",amigo.getNick());
        valores.put("_id",amigo.getId());
        valores.put("token",amigo.getRegistro());
        valores.put("imagem",amigo.getUrlfoto());

        Log.i("URLFOTOAMIGO", amigo.getUrlfoto());
        db.insert("amigos",null,valores);
    }

    public void deletar(int id_amigo){
        db.delete("amigos", "_id=" + id_amigo, null);
    }
    public void deletarTudo(){
        db.delete("amigos", null, null);
    }

    public void atualizar(Amigo amigo){
        ContentValues valores = new ContentValues();
        valores.put("nick",amigo.getNick());

        db.update("amigos", valores, "_id=?", new String[]{"" + amigo.getId()});
    }
    public List<Amigo> buscar(){
        List<Amigo> listaAmigos = new ArrayList<>();
        String[] colunas = new String[]{"_id","nome","token","imagem"};
        Cursor cursor = db.query("amigos",colunas,null,null,null,null,"nome ASC");


        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                Amigo amigo = new Amigo();

                amigo.setId(cursor.getInt(0));
                amigo.setNick(cursor.getString(1));
                amigo.setRegistro(cursor.getString(2));
                amigo.setUrlfoto(cursor.getString(3));

                listaAmigos.add(amigo);
            }while(cursor.moveToNext());
        }


        return listaAmigos;
    }
}
