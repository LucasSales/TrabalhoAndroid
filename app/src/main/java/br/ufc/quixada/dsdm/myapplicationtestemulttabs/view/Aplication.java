package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.app.Application;
import android.content.Intent;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.service.ServiceLocal;

/**
 * Created by lucas on 15/02/16.
 */
public class Aplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Intent i = new Intent(this, ServiceLocal.class);
        startService(i);
    }

}
