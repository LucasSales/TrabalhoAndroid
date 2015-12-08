package br.ufc.quixada.dsdm.myapplicationtestemulttabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lucas on 05/12/2015.
 */
public class TabAmigos extends Fragment{
    private static final String NUMERO_SESSAO = "numero_sessao";

    public static TabAmigos newInstance(int numeroSessao){
        TabAmigos fragment = new TabAmigos();
        Bundle bundle = new Bundle();
        bundle.putInt(NUMERO_SESSAO,numeroSessao);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle instanciaSalva){
        View rootView = inflater.inflate(R.layout.tab_amigos,container,false);
        return rootView;
    }

}
