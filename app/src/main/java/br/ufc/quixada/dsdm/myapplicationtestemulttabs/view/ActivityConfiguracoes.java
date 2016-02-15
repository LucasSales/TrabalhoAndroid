package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters.AdaptadorBuscaAmigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.constantes.Constantes;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.AmigoDAO;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.MensagemLocalDAO;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Usuario;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.UsuarioDAO;

public class ActivityConfiguracoes extends AppCompatActivity {

    private  ImageView foto ;
    private TextView nome;
    private UsuarioDAO userDao;
    private AmigoDAO amigoDAO;
    private MensagemLocalDAO mensagemLocalDAO;
    private List<Usuario> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_configuracoes);
        userDao = new UsuarioDAO(this);
        amigoDAO = new AmigoDAO(this);
        mensagemLocalDAO = new MensagemLocalDAO(this);

        listUser = userDao.buscar();
        if(!listUser.isEmpty()) {

            foto = (ImageView) findViewById(R.id.imageViewFoto);
            nome = (TextView) findViewById(R.id.tvNomeUser);

            Picasso.with(this).load(listUser.get(0).getUrlFoto()).resize(50, 50).centerCrop().error(R.drawable.ic_action_name).placeholder(R.drawable.ic_action_name2).into(foto);
            nome.setText(listUser.get(0).getNickname());

            Log.i("entrou Aqui", listUser.get(0).getNickname());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_configuracoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickExcluir(View view){



        String url = "http://"+Constantes.IP_SERVIDOR+"/Servidor/FronteiraDeletarUsuario.php";
        final Usuario usuario = new Usuario();


        if(!listUser.isEmpty()) {
            //Toast.makeText(getApplicationContext(), nomeBusca.getText().toString(), Toast.LENGTH_SHORT).show();
            usuario.setRegistrationId(listUser.get(0).getRegistrationId());


            new Thread(new Runnable() {
                @Override
                public void run() {
                    OkHttpClient ok = new OkHttpClient();
                    Gson gson = new Gson();

                    //VARIAVEIS QUE SERAO PASSADAS PARA O SERVIDOR
                    RequestBody rb = new FormEncodingBuilder()

                            .add("metodo", gson.toJson(usuario).toString())
                            .build();

                    //MONTANDO URL
                    Request rq = new Request.Builder()
                            .url("http://" + Constantes.IP_SERVIDOR + "/Servidor/FronteiraDeletarUsuario.php")
                            .post(rb)
                            .build();
                    Log.i("HUEHUE", "teste: " + gson.toJson(usuario).toString());

                    userDao.deletar();
                    amigoDAO.deletarTudo();
                    mensagemLocalDAO.deletarTudo();
                    LoginManager.getInstance().logOut();
                    /*Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);*/
                    finishAffinity();
                    try {
                        //EXECUTANDO
                        Response resp = ok.newCall(rq).execute();

                        String resultado = resp.body().string();

                        /*//MOSTRANDO RESPOSTA DO SERVIDOR
                        Log.i("teste", resultado);

                        GsonBuilder builder = new GsonBuilder();
                        Gson gson2 = builder.create();
                        final Amigo[] ob = gson2.fromJson(resultado, Amigo[].class);

                        final List<Amigo> amigos = Arrays.asList(ob);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                            }
                        });*/


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }















    }
}
