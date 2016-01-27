package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Adaptador_Msn_Lista_Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem_Amigos;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.service.ServiceLocal;

public class ActivityListaAmigos extends AppCompatActivity {

    private ArrayList array;
    private TextView vazio;
    private ListView listView;
    private ServiceLocal sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_amigos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        array = new ArrayList<>();
        vazio = (TextView) findViewById(R.id.textViewNenhumAmigo);
        listView = (ListView) findViewById(R.id.listViewListaAmigos2);
        sl = new ServiceLocal();


        Mensagem_Amigos msn = new Mensagem_Amigos();

        msn.setNome_amigo("Robson Cavalcante");
        msn.setImg_amigo("http://cdn.slidesharecdn.com/profile-photo-RobsonCavalcante8-96x96.jpg?cb=1443582394");


        array.add(msn);
        if(!array.isEmpty()){
            Adaptador_Msn_Lista_Amigo adapter = new Adaptador_Msn_Lista_Amigo(this, array);
            listView.setAdapter(adapter);

        }else{
            vazio.setText("Nenhum Amigo");
        }




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent inter = new Intent(getBaseContext(), ActivityBatePapo.class);
                startActivity(inter);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_mensagem, menu);
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
    public void onClickStartService(){
        // Intent i = new Intent(this, AndroidService.class);
        // startService(i);
        Toast t = Toast.makeText(this, "Start Service", Toast.LENGTH_LONG);
        sl = new ServiceLocal();
        Intent intent = new Intent(this, ServiceLocal.class);
        sl.startService(intent);
    }
    public void onClickStopService(){
        //  Intent i = new Intent(this, AndroidService.class);
        //  stopService(i);
        Toast t = Toast.makeText(this, "Stot Service", Toast.LENGTH_LONG);
        sl.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    onClickStartService();
                else
                    onClickStopService();

            }
        });

    }
}
