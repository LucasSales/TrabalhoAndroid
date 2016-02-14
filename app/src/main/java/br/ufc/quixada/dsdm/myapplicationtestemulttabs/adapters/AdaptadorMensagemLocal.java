package br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.MensagemLocal;

/**
 * Created by lucas on 09/02/16.
 */
public class AdaptadorMensagemLocal extends ArrayAdapter<MensagemLocal> {
    private List<MensagemLocal> lista;
    private Activity context;

    public AdaptadorMensagemLocal(Activity context, List<MensagemLocal> lista) {
        super(context, R.layout.layout_mensagem_enviada,lista);

        this.lista = lista;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        MensagemLocal ml = lista.get(position);
        if(ml.getEnviadoPor() == 1){
            //if (convertView == null) {
                LayoutInflater layoutInflater = context.getLayoutInflater();
                convertView = layoutInflater.inflate(R.layout.layout_mensagem_enviada, null);
            //}
            TextView txtEuMandei = (TextView) convertView.findViewById(R.id.quem_envia);
            Log.i("MENSAGEMADAPTADOR","É essa: " + ml.getMensagem());
            txtEuMandei.setText(ml.getMensagem());

           return convertView;
        }else{
            //if (convertView == null) {
                LayoutInflater layoutInflater = context.getLayoutInflater();
                convertView = layoutInflater.inflate(R.layout.layout_mensagem_recebida, null);
            //}
           TextView txtEuRecebi = (TextView) convertView.findViewById(R.id.quem_recebe);
            Log.i("teste", ml.toString());
            txtEuRecebi.setText(ml.getMensagem());

            return convertView;
        }

    }
}
