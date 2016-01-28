package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.controle.BancoDeDados;

/**
 * Created by lucas on 27/01/16.
 */
public class UsuarioDAO {
    private SQLiteDatabase db;

    public UsuarioDAO(Context context){
        BancoDeDados auxDB = new BancoDeDados(context);
        db = auxDB.getWritableDatabase();
    }

    public void inserir(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put("_id",usuario.getRegistrationId());
        valores.put("nome",usuario.getNickname());

        db.insert("usuario",null,valores);
    }
    public void atualizar(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put("nome", usuario.getNickname());

        db.update("usuario", valores, "_id=?", new String[]{"" + usuario.getRegistrationId()});
    }
    public void deletar(Usuario usuario){
        db.delete("usuario", "_id=" + usuario.getRegistrationId(), null);
    }
    public List<Usuario> buscar(){
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String[] colunas = new String[]{"_id","nome"};
        Cursor cursor = db.query("usuario",colunas,null,null,null,null,"nome ASC");


        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                Usuario user = new Usuario();

                user.setRegistrationId(cursor.getString(0));
                user.setNickname(cursor.getString(1));

                listaUsuarios.add(user);
            }while(cursor.moveToNext());
        }

        return listaUsuarios;
    }
}
