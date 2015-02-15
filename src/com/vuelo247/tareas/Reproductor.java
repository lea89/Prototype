package com.vuelo247.tareas;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vuelo247.prototype.Cancion;
import com.vuelo247.prototype.R;
import com.vuelo247.prototype.adaptadores.AdaptadorCanciones;
import com.vuelo247.prototype.librerias.ActionSlideExpandableListView;

public class Reproductor extends AsyncTask<Void,Void,Boolean>{

	Context activity;
	
	ProgressDialog pd;
	
	Resources r;
	

	MediaPlayer mediaPlayer;
	
	private int stateMediaPlayer;
	private final int stateMP_NotStarter = 0;
	private final int stateMP_Playing = 1;
	private final int stateMP_Pausing = 2;
	
	String path;
	ImageButton btnplaypause;
	
	ImageButton.OnClickListener buttonPlayPauseOnClickListener;
	
	public Reproductor(Context activity,ImageButton btn_play,String path)
	{
		r = activity.getApplicationContext().getResources();
		this.btnplaypause = btn_play;
		this.path = path;
		this.activity = activity;
	}

	@Override
	protected void onPreExecute()
	{
		pd = new ProgressDialog(activity);
		pd.setMessage(r.getString(R.string.load_player));
		pd.show();
		
		super.onPreExecute();
	}
	

	@Override
	protected Boolean doInBackground(Void... params) {
		
		initMediaPlayer();
		
		return true;
	}
	
	@Override
	public void onPostExecute(Boolean boo)
	{
		btnplaypause.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(stateMediaPlayer){
				case stateMP_NotStarter:
					mediaPlayer.start();
					stateMediaPlayer = stateMP_Playing;
					break;
				case stateMP_Playing:
					mediaPlayer.pause();
					stateMediaPlayer = stateMP_Pausing;
					break;
				case stateMP_Pausing:
					mediaPlayer.start();
					stateMediaPlayer = stateMP_Playing;
					break;
				}
				
			}
		});
		pd.dismiss();
	}
	
		
	private void initMediaPlayer()
    {
    	mediaPlayer = new  MediaPlayer();
        stateMediaPlayer = stateMP_NotStarter;
        
        try {
			mediaPlayer.setDataSource(path);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
	        mediaPlayer.setLooping(true);
	        mediaPlayer.prepare();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
