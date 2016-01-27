package br.ufc.quixada.dsdm.myapplicationtestemulttabs.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Ronson Cavalcante on 27/01/2016.
 */
public class ServiceLocal extends Service implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,com.google.android.gms.location.LocationListener {

    private  GoogleApiClient  mGoogleApiClient;
    private final IBinder mBinder = (IBinder) new ServiceLocal();
    private Location UltimoLocal;
    private Boolean Conectado;

    @Nullable
    @Override


    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {

        mGoogleApiClient = new GoogleApiClient.Builder(getBaseContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        super.onCreate();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("ServiceLocal", "Conexão Suspensa");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("ServiceLocal", "Falha na Conexão");

    }

    @Override
    public void onLocationChanged(Location location) {
        // realizar a busca das mensagens

        Log.i("ServiceLocal", "OnLocationChanged funfou  | " + location.toString());
    }

    @Override
    public void onDestroy() {
        if(mGoogleApiClient != null) {
            stopLocationUpdates();
            mGoogleApiClient.disconnect();
            Conectado = false;
        }
        super.onDestroy();
    }

    protected void startLocationUpdates() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setSmallestDisplacement(10);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        UltimoLocal = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


    }

    protected void stopLocationUpdates() {

            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }


    public Location getUltimoLocal(){
        Location l = LocationServices
                .FusedLocationApi
                .getLastLocation(mGoogleApiClient);
        if(l != null)
            UltimoLocal = l;
        return l;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mGoogleApiClient != null && !Conectado){
            mGoogleApiClient.connect();
            Conectado = true;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //Retorna a instancia do Serviço
    public class LocalBilder extends Binder{
        public ServiceLocal getServiceLocal(){
            return ServiceLocal.this;
        }
    }

}

