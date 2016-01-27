package br.ufc.quixada.dsdm.myapplicationtestemulttabs.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;

/**
 * Created by Ronson Cavalcante on 20/01/2016.
 */
public class Adaptador_Mensagens_Grupo  extends BaseExpandableListAdapter {

    private List<String> listGroup;
    private HashMap<String, List<Mensagem_Amigos>> listData;
    private LayoutInflater inflater;

    public Adaptador_Mensagens_Grupo(Context context, List<String> listGroup, HashMap<String, List<Mensagem_Amigos>> listData){
        this.listGroup = listGroup;
        this.listData = listData;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listData.get(listGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return (String) listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return (Mensagem_Amigos)listData.get(listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return (long)listData.get(listGroup.get(groupPosition)).get(childPosition).getId_mensagem();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.modelo_mensagem_grupo, null);

            TextView tvLocal = (TextView) convertView.findViewById(R.id.tvGrupo);
            tvLocal.setText(listGroup.get(groupPosition));


        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Mensagem_Amigos m  = (Mensagem_Amigos) getChild(groupPosition,childPosition);


        if(convertView == null) {
            convertView = inflater.inflate(R.layout.modelo_item_grupo, null);


            TextView tvNome = (TextView) convertView.findViewById(R.id.tvNomeGrupo_Item);
            TextView tvTrecho = (TextView) convertView.findViewById(R.id.tv_trecho_item);
            TextView tvData = (TextView) convertView.findViewById(R.id.tvData_Item);

            tvNome.setText("NomeGrupo");
            tvTrecho.setText(m.getUltimo_texto());
            tvData.setText(m.getUltima_visualizacao());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}


