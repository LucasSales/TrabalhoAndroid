/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.ufc.quixada.dsdm.myapplicationtestemulttabs.googleGCM;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.User;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.WrapObjToNetwork;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Usuario;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.UsuarioDAO;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.network.NetworkConnection;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.network.Transaction;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};
    public static final String LOG = "LOG";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String senderId = "480708892495";
        boolean status = sharedPreferences.getBoolean("status",false);
        /*try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(senderId,
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // [END get_token]
            Log.i(TAG, "GCM Registration Token: " + token);

            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer(token);

            // Subscribe to topic channels
            subscribeTopics(token);

            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
            // [END register_for_gcm]
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);*/



        synchronized (LOG){
            InstanceID instanceID = InstanceID.getInstance(this);
            try{

                if(!status){
                    String token = instanceID.getToken(senderId,
                            GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                    Log.i(LOG,"Token: "+token);

                    sharedPreferences.edit().putBoolean("status", token != null && token.trim().length() > 0).apply();

                    sendRegistrationToServer(token);
                }

            }catch(IOException e){
                e.printStackTrace();
            }
        }


    }

    /**
     * Persist registration to third-party servers.
     *
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The ne9w token.
     */
    private void sendRegistrationToServer(String token) {
        Usuario usuario = new Usuario();
        usuario.setRegistrationId(token);
        usuario.setNickname("Luan");

        String url = "http://192.168.1.30:80/Servidor/Fronteira.php";
        // Add custom implementation, as needed.
        NetworkConnection.getInstance(this).execute( new WrapObjToNetwork(usuario), RegistrationIntentService.class.getName(),url);

        cadastroUsuario(usuario,token);
    }

    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {

            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
    // [END subscribe_topics]

    public void cadastroUsuario(Usuario usuario, String token){
        boolean existe=false;
        UsuarioDAO dao = new UsuarioDAO(this);
        List<Usuario> lista = dao.buscar();
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).getRegistrationId().equals(token)){
                existe = true;
                Log.i("USER","Usuario: " + lista.get(i).getNickname());
            }
        }
        if(!existe)
            dao.inserir(usuario);
    }

}
