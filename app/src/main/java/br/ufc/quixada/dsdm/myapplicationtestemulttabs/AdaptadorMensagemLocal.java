package br.ufc.quixada.dsdm.myapplicationtestemulttabs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.MensagemLocal;

/**
 * Created by lucas on 09/02/16.
 */
public class AdaptadorMensagemLocal extends ArrayAdapter<MensagemLocal> {
    private List<MensagemLocal> lista;
    private Activity context;
    private int quemMandou;
    public AdaptadorMensagemLocal(Activity context, List<MensagemLocal> lista, int quemMandou) {
        super(context, R.layout.layout_mensagens,lista);

        this.lista = lista;
        this.context = context;
        this.quemMandou = quemMandou;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.modelo_mensagem_amigo, null);
        }
        MensagemLocal ml = lista.get(position);
        if(quemMandou == 1){
            TextView txtEuMandei = (TextView) convertView.findViewById(R.id.quem_envia);

            txtEuMandei.setText(ml.getMensagem());

            return convertView;
        }else{
            TextView txtEuRecebi = (TextView) convertView.findViewById(R.id.quem_recebe);

            txtEuRecebi.setText(ml.getMensagem());

            return convertView;
        }

    }
}
