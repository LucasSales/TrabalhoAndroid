package br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.MensagemAmigos;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.MensagemLocal;

/**
 * Created by Robson Cavalcante on 10/12/2015.
 */
public class Adaptador_Msn_Lista extends ArrayAdapter<MensagemAmigos> {

    private List<MensagemAmigos> lista;
    private Activity context;

    public Adaptador_Msn_Lista(Activity context, List<MensagemAmigos> lista) {
        super(context, R.layout.modelo_mensagem_amigo, lista);
        this.lista = lista;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.modelo_mensagem, null);
        }
        MensagemAmigos modelo = lista.get(position);

        TextView txtNomeAmigo = (TextView) convertView.findViewById(R.id.txtnomeamigo);
        TextView txtTrechoMensagem = (TextView) convertView.findViewById(R.id.txt_trecho_msn_amigo);
        TextView txtData = (TextView) convertView.findViewById(R.id.txtdata);
        ImageView imagem = (ImageView) convertView.findViewById(R.id.imageViewAmigo);

        Picasso.with(context).load(modelo.getImg_amigo()).resize(50,50).centerCrop().error(R.drawable.ic_action_name).placeholder(R.drawable.ic_action_name2).into(imagem);

        txtNomeAmigo.setText(modelo.getNome_amigo());
        txtTrechoMensagem.setText(modelo.getUltimo_texto());
        txtData.setText(modelo.getUltima_visualizacao());



        return convertView;
    }
    public void excluindoMensagemAmigo( MensagemAmigos ml, Adaptador_Msn_Lista adapter){
        if(adapter != null)
            adapter.remove(ml);
        adapter.notifyDataSetChanged();

    }
}
