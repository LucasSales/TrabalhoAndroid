package br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem_Amigos;

/**
 * Created by lucas on 28/01/16.
 */
public class AdaptadorBuscaAmigo extends ArrayAdapter<Amigo> {
    public AdaptadorBuscaAmigo(Activity context, Amigo[] lista) {
        super(context, R.layout.amigo_busca, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
