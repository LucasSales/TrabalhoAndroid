package br.ufc.quixada.dsdm.myapplicationtestemulttabs.View;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.Model.Adaptador_Msn_Lista_Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.Model.Mensagem_Amigos;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;

/**
 * Created by Lucas on 05/12/2015.
 */
public class TabAmigos extends Fragment{
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
        View rootView = inflater.inflate(R.layout.tab_amigos,container,false);

        vazio = (TextView) rootView.findViewById(R.id.textViewVazio);
        listView = (ListView) rootView.findViewById(R.id.listViewMensagemAmigos);
        Array = new ArrayList<>();

        return rootView;


    }

    @Override
    public void onStart() {
        super.onStart();

        Mensagem_Amigos msn = new Mensagem_Amigos();

        msn.setNome_amigo("Lucas Sales");
        msn.setUltima_visualizacao("10:50");
        msn.setUltimo_texto("Testando se funfou");
        msn.setImg_amigo("https://www.google.com.br/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwiYk4PctNTJAhUDhJAKHQQsDw0QjRwIBw&url=http%3A%2F%2Fwww.ofuxico.com.br%2Fnoticias-sobre-famosos%2Fjustin-bieber-passeia-sem-camisa-pelas-ruas-de-cannes%2F2014%2F05%2F20-203759.html&psig=AFQjCNEGmDFDGzkJ4EnGtCqZtECBwLBveQ&ust=1449944054120212");


        Array.add(msn);
        if(!Array.isEmpty()){
            Adaptador_Msn_Lista_Amigo adapter = new Adaptador_Msn_Lista_Amigo(getActivity(), Array);
            listView.setAdapter(adapter);

        }else{
            vazio.setText("Nenhuma Nota");
        }

    }
}
