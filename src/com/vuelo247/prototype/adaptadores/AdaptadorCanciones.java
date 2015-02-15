package com.vuelo247.prototype.adaptadores;

import java.util.ArrayList;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vuelo247.prototype.Cancion;
import com.vuelo247.prototype.R;
import com.vuelo247.tareas.Reproductor;

public class AdaptadorCanciones extends BaseAdapter{

	Context context;
    ArrayList<Cancion> canciones;
    
    MediaPlayer mp;
    boolean isPLAYING= false;
    boolean pathLoad = false;
    int length;
    String url;

    public AdaptadorCanciones(Context context, ArrayList<Cancion> canciones)
    {
        this.context = context;
        this.canciones = canciones;
        
        url = context.getResources().getString(R.string.url_server);
    }
    
	@Override
	public int getCount() {
		return canciones.size();
	}

	@Override
	public Cancion getItem(int position) {
		return canciones.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	private class ViewHolder
	{
		TextView txt_nombre_cancion;
		TextView txt_album_cancion;
		TextView txt_duracion_cancion;
		ImageButton btn_play;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		 final Cancion cancion = getItem(position);

	        final ViewHolder holder;

	        if(convertView == null)
	        {
	            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	            convertView = li.inflate(R.layout.renglon_cancion,parent,false);

	            holder = new ViewHolder();

	            holder.txt_album_cancion = (TextView) convertView.findViewById(R.id.txt_album_cancion);
	            holder.txt_duracion_cancion = (TextView) convertView.findViewById(R.id.txt_duracion_cancion);
	            holder.txt_nombre_cancion = (TextView) convertView.findViewById(R.id.txt_nombre_cancion);
	            holder.btn_play = (ImageButton) convertView.findViewById(R.id.btn_play);

	            convertView.setTag(holder);
	        }
	        else
	        {
	            holder = (ViewHolder) convertView.getTag();
	        }

	        holder.txt_nombre_cancion.setText(cancion.getNombre());
	        holder.txt_album_cancion.setText(cancion.getAlbum());
	        holder.txt_duracion_cancion.setText(cancion.getDuracion());
	        
	        holder.btn_play.setOnClickListener(new OnClickListener()
	        {

				@Override
				public void onClick(View v) {
					new Reproductor(context,holder.btn_play,url+cancion.getPath().toString()).execute();
				}
	        	
	        });
	        
	        return convertView;
	}
}
