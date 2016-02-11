package br.ufc.quixada.dsdm.myapplicationtestemulttabs.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.constantes.Constantes;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.WrapObjToNetwork;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.googleGCM.RegistrationIntentService;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Local;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Usuario;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.UsuarioDAO;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.VerificaMensagem;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.network.NetworkConnection;

public class ServiceLocal extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{


    private GoogleApiClient mGoogleApiClient;
    private final IBinder mBinder = new LocalBinder();
    public boolean playConnected = false;
    private Location lastLocation;


    @Override
    public void onCreate() {
        Log.i("Android Service", "entrou onCreate");
        mGoogleApiClient = new GoogleApiClient.Builder(getBaseContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.i("Android Service", "onDestroy");
        if(mGoogleApiClient != null) {
            pararAtualizacaoLocalizacao();
            mGoogleApiClient.disconnect();
            playConnected = false;
        }
        super.onDestroy();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Android Service", "onStartCommand");
        if(mGoogleApiClient != null && !playConnected){
            mGoogleApiClient.connect();
            playConnected = true;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("Android Service", "Conectado Serviço");
        iniciarAtualizacaoLocalizacao();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("Android Service", "Conexao Suspendida");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("Android Service", "Conexao1 falhou");
    }


    @Override
    public void onLocationChanged(Location location) {


        Log.i("Android Service", "Localização:" + location.toString());


    }

    protected void iniciarAtualizacaoLocalizacao() {
        Log.d("Android Service", "Entrou IniciarAtualização");
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setSmallestDisplacement(1);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


        UsuarioDAO dao = new UsuarioDAO(this);
        List<Usuario> usuario = dao.buscar();

        if(usuario.size() > 0){
            String token = usuario.get(0).getRegistrationId();


            Location local = this.getUltimaLocalizacao();
            Local localizacao = new Local();
            localizacao.setLatitude(local.getLatitude());
            localizacao.setLongitude(local.getLongitude());

            VerificaMensagem temMsg = new VerificaMensagem(localizacao,token);

            String url = "http://"+Constantes.IP_SERVIDOR+"/Servidor/FronteiraPegarMSG.php";
            // Add custom implementation, as needed.
            NetworkConnection.getInstance(this).execute(new WrapObjToNetwork(temMsg), RegistrationIntentService.class.getName(), url);
        }

    }

    protected void pararAtualizacaoLocalizacao() {
        Log.d("Android Service", "pararAtualizacaoLocalizacao()");
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }


    public Location getUltimaLocalizacao(){
        if (mGoogleApiClient != null) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }

        return lastLocation;
    }

    public class LocalBinder extends Binder {
        public ServiceLocal getService() {
            return ServiceLocal.this;
        }
    }
}
