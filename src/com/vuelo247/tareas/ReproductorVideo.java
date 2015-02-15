package com.vuelo247.tareas;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.vuelo247.prototype.R;

public class ReproductorVideo extends AsyncTask<Void,Void,Boolean>{
	
	
	VideoView video_player_view;
	DisplayMetrics dm;
	SurfaceView sur_View;
	MediaController media_Controller;
	String path;
	Activity activity;
	
	public ReproductorVideo(MediaController mc, VideoView vv,String path,Activity ac)
	{
		media_Controller = mc;
		video_player_view = vv;
		this.path = path;
		activity = ac;
	}

	public void getInit() {
		dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int height = dm.heightPixels;
		int width = dm.widthPixels;
//		video_player_view.setMinimumWidth(width);
//		video_player_view.setMinimumHeight(height);
		media_Controller.setMediaPlayer(video_player_view);
		video_player_view.setMediaController(media_Controller);
		video_player_view.setVideoPath("http://elplandelamariposa.vuelo247.com.ar/uploads/videos/hola.mp4");
		video_player_view.requestFocus(); 
		video_player_view.start();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		
		try {
			getInit();
	    } catch (Exception e) {
	        Toast.makeText(activity, "Error connecting", Toast.LENGTH_SHORT).show();
	    }
		
		return null;
	}
}
