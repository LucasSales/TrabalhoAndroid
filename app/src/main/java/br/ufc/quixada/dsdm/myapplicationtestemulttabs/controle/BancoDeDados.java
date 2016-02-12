package br.ufc.quixada.dsdm.myapplicationtestemulttabs.controle;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lucas on 27/01/16.
 */
public class BancoDeDados extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "mensageiro";

    public BancoDeDados(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(" +
                "_id text primary key," +
                "nome text not null" +
                ");");
        db.execSQL("create table amigos(" +
                "_id integer primary key," +
                "nome text not null," +
                "token text not null" +
                ");");
        db.execSQL("create table mensagem(" +
                "_idAmigo integer," +
                "texto text not null," +
                "nomeAmigo text" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table usuario;");
        onCreate(db);
    }
}
