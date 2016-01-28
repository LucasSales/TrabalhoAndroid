package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.WrapObjToNetwork;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.network.NetworkConnection;

public class ActivityAdicionarAmigo extends AppCompatActivity {
    ImageButton imageButton;
    int icon = R.drawable.ic_pageview_black_24dp;
    EditText nomeBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_adicionar_amigo);

        imageButton = (ImageButton) findViewById(R.id.imageButton);

        imageButton.setImageResource(icon);

    }

    public void buscarAmigo(View view){

        String url = "http://192.168.1.10:80/Servidor/FronteiraBusca.php";
        Amigo amigo = new Amigo();

        nomeBusca = (EditText) findViewById(R.id.editTextNomeBusca);


        if(nomeBusca.getText().toString() != null){
            Toast.makeText(getApplicationContext(), nomeBusca.toString(), Toast.LENGTH_SHORT).show();
            //amigo.setNickname(nomeBusca.toString());
            //NetworkConnection.getInstance(this).executeBuscaAmigo(new WrapObjToNetwork(amigo), ActivityAdicionarAmigo.class.getName(), url);
        }

    }


}
