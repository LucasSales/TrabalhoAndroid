package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters.Adaptador_Mensagens_BatePapo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.WrapObjToNetwork;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem_Amigos;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Usuario;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.UsuarioDAO;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.network.NetworkConnection;

/**
 * Created by Robson Cavalcante on 18/12/2015.
 */
public class ActivityBatePapo extends AppCompatActivity{

    private ArrayList Array;
    private ListView listView;
    private TextView vazio;
    private Adaptador_Mensagens_BatePapo adapter;
    private Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bate_papo);

        Array = new ArrayList();
        listView = (ListView)findViewById(R.id.listViewMsnBatePapo);
        vazio = (TextView) findViewById(R.id.textViewNenhumaMsn);



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

        Mensagem_Amigos msn = new Mensagem_Amigos();
         msn.setTexto("funfou");
         msn.setUltima_visualizacao("10:00");


        Array.add(msn);
        if(!Array.isEmpty()){
            adapter = new Adaptador_Mensagens_BatePapo(this, Array);
            listView.setAdapter(adapter);

        }else{
            vazio.setText("Nenhuma Mensagem");
        }

    }

    public void mandarMsg(View view){
        UsuarioDAO dao = new UsuarioDAO(this);
        List<Usuario> usuarios = dao.buscar();

        id = getIntent().getIntExtra("id", -1);
        Log.i("ID","id: " + id);
        String url = "http://192.168.1.30:80/Servidor/Fronteira.php";
        Mensagem msg = new Mensagem();

        TextView tx = (TextView) findViewById(R.id.msgArea);
        msg.setMessage(tx.getText().toString());
        msg.getIdFrom(usuarios.get(0).getRegistrationId());
        msg.setIdTo(id.toString());
        msg.setLatitude(123);
        msg.setLongitude(123);
        // Add custom implementation, as needed.
        NetworkConnection.getInstance(this).execute(new WrapObjToNetwork(usuario), ActivityBatePapo.class.getName(), url);




    }

}
