package br.ufc.quixada.dsdm.myapplicationtestemulttabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lucas on 05/12/2015.
 */
public class PlaceholderFragment extends Fragment {
    private static final String NUMERO_SESSAO = "numero_sessao";

    public static PlaceholderFragment newInstance(int numeroSessao){
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(NUMERO_SESSAO, numeroSessao);
        fragment.setArguments(bundle);
        return fragment;
    }
    public PlaceholderFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container,Bundle salvarInstancia){
        View rootView = layoutInflater.inflate(R.layout.fragment_main,container,false);
        return rootView;
    }

}
