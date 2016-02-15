package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.constantes.Constantes;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Usuario;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.UsuarioDAO;

public class ActivityConfiguracoes extends AppCompatActivity {

    private  ImageView foto ;
    private TextView nome;
    private UsuarioDAO userDao;
    private List<Usuario> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_configuracoes);
        userDao = new UsuarioDAO(this);

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
}
