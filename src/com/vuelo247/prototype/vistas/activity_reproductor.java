package com.vuelo247.prototype.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.vuelo247.prototype.R;
import com.vuelo247.tareas.ReproductorVideo;

public class activity_reproductor extends Activity{

	VideoView video;
	
	Intent intent;
	
	MediaController mc;
	
	protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.activity_reproductor);
        
        levantarXML();
        
        Intent intent = getIntent();
        
        mc = new MediaController(this);
        
        new ReproductorVideo(mc, video,intent.getExtras().getString("path"),activity_reproductor.this).execute();
    }
	
	public void levantarXML()
	{
		video = (VideoView) findViewById(R.id.video_player_view);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		video.stopPlayback();
		mc.clearAnimation();
		video.clearAnimation();
		System.gc();
	}
}
