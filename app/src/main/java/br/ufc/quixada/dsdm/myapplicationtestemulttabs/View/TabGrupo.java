package br.ufc.quixada.dsdm.myapplicationtestemulttabs.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.Model.Adaptador_Msn_Lista;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.Model.Adaptador_Msn_Lista_Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.Model.Mensagem_Amigos;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;

/**
 * Created by Lucas on 05/12/2015.
 */
public class TabGrupo extends Fragment {
    private static final String NUMERO_SESSAO = "numero_sessao";

    private TextView vazio;
    private ListView listView;
    private ArrayList<Mensagem_Amigos> Array;

    public static TabGrupo newInstance(int numeroSessao){
        TabGrupo fragment = new TabGrupo();
        Bundle bundle = new Bundle();
        bundle.putInt(NUMERO_SESSAO, numeroSessao);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle instanciaSalva){
        final View rootView = inflater.inflate(R.layout.tab_grupo,container,false);

        vazio = (TextView) rootView.findViewById(R.id.textViewVazioGrupo);
        listView = (ListView) rootView.findViewById(R.id.listViewMsnGrupo);
        Array = new ArrayList<>();


        Mensagem_Amigos msn = new Mensagem_Amigos();

        msn.setNome_amigo("Grupo Teste");
        msn.setUltima_visualizacao("10:50");
        msn.setUltimo_texto("Ultimo texto");
        msn.setImg_amigo("http://pre07.deviantart.net/e5e6/th/pre/f/2011/036/7/9/homer_simpson___06___simpsons_by_frasier_and_niles-d38uqts.jpg");



        Array.add(msn);
        if(!Array.isEmpty()){
            Adaptador_Msn_Lista adapter = new Adaptador_Msn_Lista(getActivity(), Array);
            listView.setAdapter(adapter);

        }else{
            vazio.setText("Nenhum Grupo");
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
