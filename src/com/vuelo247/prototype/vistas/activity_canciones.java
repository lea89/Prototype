package com.vuelo247.prototype.vistas;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.vuelo247.prototype.R;
import com.vuelo247.tareas.TraerCanciones;


public class activity_canciones extends ActionBarActivity{

	boolean isPLAYING= false;
	MediaPlayer mp;
    @Override
    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.activity_canciones);
        
        new TraerCanciones(activity_canciones.this).execute();
    }
}