package br.ufc.quixada.dsdm.myapplicationtestemulttabs.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Ronson Cavalcante on 27/01/2016.
 */
public class ServiceLocal extends Service{


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
