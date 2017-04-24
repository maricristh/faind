package com.example.mahri.loginestudo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Vinicius on 20/04/2017.
 */

public class ItemAdapter extends ArrayAdapter<Item> {
    private Activity activity;
    private int layoutResourceId;
    private ArrayList<Item> data = new ArrayList<Item>();

    public ItemAdapter(Activity activity, int layoutResourceId, ArrayList<Item> data) {
        super(activity, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.activity = activity;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (row == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.nome = (TextView) row.findViewById(R.id.txtTitulo);
            holder.local = (TextView) row.findViewById(R.id.txtLocal);
            holder.palavrachave = (TextView) row.findViewById(R.id.txtPalavrasChave);
            holder.data = (TextView) row.findViewById(R.id.txtData);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Item item = data.get(position);

        holder.nome.setText(item.getCnome());
        holder.local.setText(item.getClocal());
        holder.palavrachave.setText(item.getCpalavrachave());
        holder.data.setText(activity.getApplicationContext().getString(R.string.lbl_ocorrido_em, dateFormat.format(item.getCdataevento())));
        return row;
    }

    static class ViewHolder {
        TextView nome;
        TextView local;
        TextView palavrachave;
        TextView data;
    }
}