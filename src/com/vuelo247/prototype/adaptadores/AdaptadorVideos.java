package com.vuelo247.prototype.adaptadores;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.vuelo247.prototype.R;
import com.vuelo247.prototype.Video;
import com.vuelo247.prototype.vistas.activity_reproductor;

public class AdaptadorVideos extends BaseAdapter{
	
	Context context;
	
	ArrayList<Video> list_videos;
	
	String url;
	
	public AdaptadorVideos(Context context, ArrayList<Video> list_videos)
	{
		this.context = context;
		this.list_videos = list_videos;
		
		url = context.getResources().getString(R.string.url_server);
	}

	@Override
	public int getCount() {

		return list_videos.size();
	}

	@Override
	public Video getItem(int position) {

		return list_videos.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	private class ViewHolder
	{
		TextView txt_nombre_video;
		TextView txt_album_video;
		TextView txt_duracion_video;
		TextView txt_path_video;
		
		Button btn_ver_video;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		 final Video video = getItem(position);

	        final ViewHolder holder;

	        if(convertView == null)
	        {
	            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	            convertView = li.inflate(R.layout.renglon_video,parent,false);

	            holder = new ViewHolder();

	            holder.txt_album_video = (TextView) convertView.findViewById(R.id.txt_album_video);
	            holder.txt_duracion_video = (TextView) convertView.findViewById(R.id.txt_duracion_video);
	            holder.txt_nombre_video = (TextView) convertView.findViewById(R.id.txt_nombre_video);
	            holder.txt_path_video = (TextView) convertView.findViewById(R.id.txt_path_video);
	            holder.btn_ver_video = (Button) convertView.findViewById(R.id.btn_ver_video);

	            convertView.setTag(holder);
	        }
	        else
	        {
	            holder = (ViewHolder) convertView.getTag();
	        }

	        holder.txt_nombre_video.setText(video.getNombre());
	        holder.txt_album_video.setText(video.getAlbum());
	        holder.txt_duracion_video.setText(video.getDuracion());
	        holder.txt_path_video.setText(video.getPath());
	        
	        holder.btn_ver_video.setOnClickListener(new OnClickListener()
	        {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context.getApplicationContext(),activity_reproductor.class);
					
					intent.putExtra("path", video.getPath());
					
					context.startActivity(intent);
					
//					ReproductorVideo(context,holder.btn_play,url+video.getPath().toString()).execute();
				}
	        	
	        });
	        
	        return convertView;
	}

}
