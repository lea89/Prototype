package com.vuelo247.prototype.adaptadores;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vuelo247.prototype.Evento;
import com.vuelo247.prototype.R;
import com.vuelo247.prototype.vistas.activity_detalle_evento;

public class AdaptadorEventos extends BaseAdapter{

    Context context;
    ArrayList<Evento> eventos;
    Evento evento;
    Intent intent;

    public AdaptadorEventos(Context context, ArrayList<Evento> eventos)
    {
        this.context = context;
        this.eventos = eventos;
    }
    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Evento getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder
    {
        ImageView img_evento;
        TextView txt_nombre_evento;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        evento = getItem(position);

        ViewHolder holder;

        if(convertView == null)
        {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = li.inflate(R.layout.renglon_evento,parent,false);

            holder = new ViewHolder();

            holder.img_evento = (ImageView) convertView.findViewById(R.id.img_evento);
            holder.txt_nombre_evento = (TextView) convertView.findViewById(R.id.txt_evento);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_nombre_evento.setText(evento.getNombre());
        holder.img_evento.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
        
        return convertView;
    }
}
