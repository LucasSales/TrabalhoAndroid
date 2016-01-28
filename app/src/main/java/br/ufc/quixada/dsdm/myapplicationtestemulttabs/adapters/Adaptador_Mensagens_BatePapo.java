package br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem_Amigos;

/**
 * Created by Robson Cavalcante on 10/12/2015.
 */
public class Adaptador_Mensagens_BatePapo extends ArrayAdapter<Mensagem_Amigos> {

    private List<Mensagem_Amigos> lista;
    private Activity context;

    public Adaptador_Mensagens_BatePapo(Activity context, List<Mensagem_Amigos> lista) {
        super(context, R.layout.modelo_mensagem_amigo, lista);
        this.lista = lista;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.modelo_bate_papo, null);
        }
        Mensagem_Amigos modelo = lista.get(position);

        TextView txtMensagem = (TextView) convertView.findViewById(R.id.tvMsnBatePapo);
        TextView txtData = (TextView) convertView.findViewById(R.id.tvDataMsnBatePapo);


        txtMensagem.setText(modelo.getNome_amigo());
        txtData.setText(modelo.getUltima_visualizacao());



        return convertView;
    }
}
