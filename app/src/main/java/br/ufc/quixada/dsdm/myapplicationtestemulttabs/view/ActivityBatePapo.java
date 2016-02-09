package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.AdaptadorMensagemLocal;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters.Adaptador_Mensagens_BatePapo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.WrapObjToNetwork;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Local;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.MensagemLocal;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.MensagemLocalDAO;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem_Amigos;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Usuario;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.UsuarioDAO;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.network.NetworkConnection;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.service.ServiceLocal;

/**
 * Created by Robson Cavalcante on 18/12/2015.
 */
public class ActivityBatePapo extends AppCompatActivity{

    private ArrayList listaAmigos;
    private ListView listView;
    private TextView vazio;
    private Adaptador_Mensagens_BatePapo adapter;
    private Integer id;
    private  ServiceLocal service;
    private boolean conectado = false;
    private ImageView imgBtn;
    private AdaptadorMensagemLocal adml;
    private MensagemLocalDAO mensagemLocalDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bate_papo);

        listaAmigos = new ArrayList();
        listView = (ListView)findViewById(R.id.listViewMsnBatePapo);
        vazio = (TextView) findViewById(R.id.textViewNenhumaMsn);

        imgBtn = (ImageView) findViewById(R.id.enviarMsg);
        imgBtn.setImageResource(R.drawable.ic_send_black_24dp);

        Intent i = new Intent(this, ServiceLocal.class);
        startService(i);
        mensagemLocalDAO = new MensagemLocalDAO(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bate_papo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this,ActivityConfiguracoes.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        bindService(new Intent(this, ServiceLocal.class), mConnection,
                Context.BIND_AUTO_CREATE);

        if(!listaAmigos.isEmpty()){
            adapter = new Adaptador_Mensagens_BatePapo(this, listaAmigos);
            listView.setAdapter(adapter);

        }else{
            vazio.setText("Nenhuma Mensagem");
        }

    }



    public void mandarMsg(View view){
        UsuarioDAO dao = new UsuarioDAO(this);
        List<Usuario> usuarios = dao.buscar();

        //pega o id do amigo que eu estou enviando a msg
        id = getIntent().getIntExtra("id", -1);

        final String url = "http://192.168.129.147:80/Servidor/FronteiraCadastroMSG.php";

        final Mensagem msg = new Mensagem();

        TextView tx = (TextView) findViewById(R.id.msgArea);
        if(conectado){
            Location local = ActivityBatePapo.this.service.getUltimaLocalizacao();
            Local localizacao = new Local();
            localizacao.setLatitude(local.getLatitude());
            localizacao.setLongitude(local.getLongitude());

            String token = usuarios.get(0).getRegistrationId();
            msg.setMessage(tx.getText().toString());
            msg.setIdFrom(token);
            msg.setIdTo(id.toString());
            msg.setLocal(localizacao);

            //teste msg adapter
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MensagemLocal msglocal = new MensagemLocal();
                    msglocal.setMensagem(msg.getMessage());
                    msglocal.setIdAmigo(id);

                    mensagemLocalDAO.inserir(msglocal);


                    NetworkConnection.getInstance(ActivityBatePapo.this).execute(new WrapObjToNetwork(msg), ActivityBatePapo.class.getName(), url);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<MensagemLocal> listaMsgLocal = new ArrayList<>();
                            listaMsgLocal = mensagemLocalDAO.buscar(id);


                            //Log.i("ID", "id: " + ml.getMensagem().toString());


                            if(listaMsgLocal.size() > 0) {
                                adml = new AdaptadorMensagemLocal(ActivityBatePapo.this, listaMsgLocal, 1);

                                listView.setAdapter(adml);
                            }

                            Log.i("ID", "id: " + id);
                        }
                    });



                }
            }).start();
            //fim teste
            tx.setText(null);

            //É melhor sem isso parece q da mais certo sem desturir o serviço
            //service.onDestroy();

        }
        // Add custom implementation, as needed.





    }
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ServiceLocal.LocalBinder binder = (ServiceLocal.LocalBinder) service;
            ActivityBatePapo.this.service =  binder.getService();
            conectado = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            conectado = false;
        }
    };

}
