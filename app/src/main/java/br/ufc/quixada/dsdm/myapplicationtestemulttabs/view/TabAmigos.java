package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters.Adaptador_Msn_Lista_Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.AmigoDAO;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.MensagemAmigos;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.MensagemLocalDAO;

/**
 * Created by Lucas on 05/12/2015.
 */
public class  TabAmigos extends Fragment{
    private static final String NUMERO_SESSAO = "numero_sessao";


    private TextView vazio;
    private ListView listView;
    private List<Amigo> Array;
    private  AmigoDAO amigoDAO;
    Adaptador_Msn_Lista_Amigo adapter;


    public static TabAmigos newInstance(int numeroSessao){
        TabAmigos fragment = new TabAmigos();
        Bundle bundle = new Bundle();
        bundle.putInt(NUMERO_SESSAO, numeroSessao);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle instanciaSalva){
        final View rootView = inflater.inflate(R.layout.tab_amigos,container,false);

        amigoDAO = new AmigoDAO(getActivity());

        vazio = (TextView) rootView.findViewById(R.id.textViewVazio);
        listView = (ListView) rootView.findViewById(R.id.listViewMensagemAmigos);
        Array = amigoDAO.buscar();

        if(!Array.isEmpty()){
           adapter = new Adaptador_Msn_Lista_Amigo(getActivity(), Array);
            listView.setAdapter(adapter);

        }else{
            vazio.setText("Nenhuma Mensagem");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Amigo a = TabAmigos.this.Array.get(position);
                Intent inter = new Intent(rootView.getContext(), ActivityBatePapo.class);
                //mando o id do amig q ta no BD para o listAmigos
                inter.putExtra("id", a.getId());
                inter.putExtra("nomeAmigo", a.getNick());
                inter.putExtra("token", a.getRegistro());
                Log.i("TABAMIGOS", a.getRegistro());
                startActivity(inter);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, final int pos, long id) {


                AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
                builderSingle.setIcon(R.drawable.mr_ic_settings_dark);
                builderSingle.setTitle(R.string.opcaoAlertDialog);

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("Excluir");


                builderSingle.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Your Selected Item is");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                // excluindo mensagem
                                Amigo a = TabAmigos.this.Array.get(pos);

                                amigoDAO.deletar(a.getId());
                                adapter.excluindoMensagemAmigo(a, adapter);

                            }
                        });
                        builderInner.show();
                    }
                });
                builderSingle.show();


                return true;
            }
        });

        return rootView;


    }

   public void atualizarLista(){
       Array = amigoDAO.buscar();

       if(!Array.isEmpty()){
           adapter = new Adaptador_Msn_Lista_Amigo(getActivity(), Array);
           listView.setAdapter(adapter);

       }else{
           vazio.setText("Nenhum Amigo");
       }
    }
}
