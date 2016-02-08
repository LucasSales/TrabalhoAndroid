package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters.AdaptadorBuscaAmigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.AmigoDAO;

public class ActivityAdicionarAmigo extends AppCompatActivity {
    ImageButton imageButton;
    int icon = R.drawable.ic_pageview_black_24dp;
    EditText nomeBusca;
    ListView listView;
    AdaptadorBuscaAmigo adaptadorBuscaAmigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_adicionar_amigo);
        listView = (ListView)findViewById(R.id.listViewBuscaAmigo);

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setImageResource(icon);


    }

    public void buscarAmigo(View view){

        String url = "http://192.168.129.147:80/Servidor/FronteiraBusca.php";
        final Amigo amigo = new Amigo();

        nomeBusca = (EditText) findViewById(R.id.editTextNomeBusca);

        if(nomeBusca.getText().toString() != null){
            //Toast.makeText(getApplicationContext(), nomeBusca.getText().toString(), Toast.LENGTH_SHORT).show();
            amigo.setNick(nomeBusca.getText().toString());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    OkHttpClient ok = new OkHttpClient();
                    Gson gson = new Gson();

                    //VARIAVEIS QUE SERAO PASSADAS PARA O SERVIDOR
                    RequestBody rb = new FormEncodingBuilder()

                            .add("metodo", gson.toJson(amigo).toString())
                            .build();

                    //MONTANDO URL
                    Request rq = new Request.Builder()
                            .url("http://192.168.129.147:80/Servidor/FronteiraBusca.php")
                            .post(rb)
                            .build();
                    Log.i("HUEHUE", "teste: " + gson.toJson(amigo).toString());
                    try {
                        //EXECUTANDO
                        Response resp = ok.newCall(rq).execute();

                        String resultado = resp.body().string();

                        //MOSTRANDO RESPOSTA DO SERVIDOR
                        Log.i("teste", resultado);

                        GsonBuilder builder = new GsonBuilder();
                        Gson gson2 = builder.create();
                        final Amigo[] ob = gson2.fromJson(resultado, Amigo[].class);

                        final List<Amigo> amigos = Arrays.asList(ob);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adaptadorBuscaAmigo = new AdaptadorBuscaAmigo(ActivityAdicionarAmigo.this, amigos, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AmigoDAO amigoDAO = new AmigoDAO(ActivityAdicionarAmigo.this);
                                        Button button = (Button) v;
                                        Amigo amigo = (Amigo) button.getTag();

                                        amigoDAO.inserir(amigo);

                                        Toast.makeText(getApplicationContext(), "add "+amigo.getNick(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                listView.setAdapter(adaptadorBuscaAmigo);


                            }
                        });


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }



}
