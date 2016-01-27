package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Adaptador_Msn_Lista_Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem_Amigos;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;

/**
 * Created by Lucas on 05/12/2015.
 */
public class  TabAmigos extends Fragment{
    private static final String NUMERO_SESSAO = "numero_sessao";


    private TextView vazio;
    private ListView listView;
    private ArrayList<Mensagem_Amigos> Array;


    public static TabAmigos newInstance(int numeroSessao){
        TabAmigos fragment = new TabAmigos();
        Bundle bundle = new Bundle();
        bundle.putInt(NUMERO_SESSAO,numeroSessao);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle instanciaSalva){
        final View rootView = inflater.inflate(R.layout.tab_amigos,container,false);

        vazio = (TextView) rootView.findViewById(R.id.textViewVazio);
        listView = (ListView) rootView.findViewById(R.id.listViewMensagemAmigos);
        Array = new ArrayList<>();

        Mensagem_Amigos msn = new Mensagem_Amigos();

        msn.setNome_amigo("Robson Cavalcante");
        msn.setImg_amigo("http://cdn.slidesharecdn.com/profile-photo-RobsonCavalcante8-96x96.jpg?cb=1443582394");

        Log.i("Error", "Iniciou Amigos");

        Array.add(msn);
        if(!Array.isEmpty()){
            Adaptador_Msn_Lista_Amigo adapter = new Adaptador_Msn_Lista_Amigo(getActivity(), Array);
            listView.setAdapter(adapter);

        }else{
            vazio.setText("Nenhuma Mensagem");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent inter = new Intent(rootView.getContext(), ActivityBatePapo.class);
                startActivity(inter);


            }
        });



        return rootView;


    }
}
