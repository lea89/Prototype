package com.vuelo247.prototype.vistas;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.vuelo247.prototype.R;
import com.vuelo247.tareas.TraerVideos;

public class activity_multimedia extends ActionBarActivity {

	ListView list_videos;
	
    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_multimedia);
        
        new TraerVideos(activity_multimedia.this).execute();
        levantarXML();
    }
    
    private void levantarXML()
    {
    	list_videos = (ListView) findViewById(R.id.list_videos);
    }
}
