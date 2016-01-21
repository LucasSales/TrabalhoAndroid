package br.ufc.quixada.dsdm.myapplicationtestemulttabs.Model;

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

    private List<Mensagem_Grupo> listGroup;
    private HashMap<Long, List<Mensagem_Amigos>> listData;
    private LayoutInflater inflater;

    public Adaptador_Mensagens_Grupo(Context context, List<Mensagem_Grupo> listGroup, HashMap<Long, List<Mensagem_Amigos>> listData){
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
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listData.get(listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return listGroup.get(groupPosition).getIdMensagem();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return listData.get(listGroup.get(groupPosition)).get(childPosition).getId_mensagem();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.modelo_mensagem_grupo, null);
            holder = new ViewHolderGroup();
            convertView.setTag(holder);

            holder.tvLocal = (TextView)convertView.findViewById(R.id.tvGrupo);
        }else{
            holder = (ViewHolderGroup)convertView.getTag();
        }

        holder.tvLocal.setText(listGroup.get(groupPosition).getLocal());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem holder;
        String trecho = (String) getChild(groupPosition, childPosition);
        String nomeGrupo = (String) getChild(groupPosition, childPosition);
        String data = (String) getChild(groupPosition, childPosition);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.modelo_item_grupo, null);
            holder = new ViewHolderItem();
            convertView.setTag(holder);

            holder.tvNome = (TextView)convertView.findViewById(R.id.tvNomeGrupo_Item);
            holder.tvTrecho = (TextView)convertView.findViewById(R.id.tv_trecho_item);
            holder.tvData = (TextView)convertView.findViewById(R.id.tvData_Item);

        }else{
            holder = (ViewHolderItem)convertView.getTag();
        }

        holder.tvNome.setText(nomeGrupo);
        holder.tvTrecho.setText(trecho);
        holder.tvData.setText(data);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolderGroup{
        TextView tvLocal;

    }
    class ViewHolderItem{
        TextView tvNome;
        TextView tvTrecho;
        TextView tvData;
    }


}


