package br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Amigo;

/**
 * Created by lucas on 28/01/16.
 */
public class AdaptadorBuscaAmigo extends ArrayAdapter<Amigo> {

    private List<Amigo> amigos;
    private Activity context;
    private View.OnClickListener onClickListener;

    public AdaptadorBuscaAmigo(Activity context, List<Amigo> lista, View.OnClickListener onClickListener) {
        super(context, R.layout.amigo_busca, lista);
        this.amigos = lista;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.amigo_busca, null);
        }
        Amigo amigo = amigos.get(position);

        Log.i("AMIGO","gente: " + amigo.getNick());

        TextView txtNick = (TextView) convertView.findViewById(R.id.txtViewnomeAmigo);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgViewBuscaAmigo);

        Button button = (Button) convertView.findViewById(R.id.btnAddAmigo);
        button.setOnClickListener(onClickListener);
        button.setTag(amigos.get(position));

        img.setImageResource(R.drawable.ic_person_add_white_24dp);

        txtNick.setText(amigo.getNick());


        return convertView;
    }

}
