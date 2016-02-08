package br.ufc.quixada.dsdm.myapplicationtestemulttabs.controle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.view.MainActivityTabMensagens;

/**
 * Created by lucas on 08/02/16.
 */
public class BroadCastMsg extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String msgs = (String) bundle.get("mensagem");
            Log.i("BROADCAST", msgs.toString());
            String mensagens = intent.getStringExtra("mensagem");


            Gson gson = new Gson();
            final String[] ob = gson.fromJson(mensagens, String[].class);

            List<String> listMensagens = Arrays.asList(ob);
            for(int i = 0; i<listMensagens.size();i++){
                Log.i("BROADCAST","mensagens :" + listMensagens.get(i).toString());
            }

        }

    }

}
