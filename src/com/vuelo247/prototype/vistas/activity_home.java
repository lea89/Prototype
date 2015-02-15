package com.vuelo247.prototype.vistas;

import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.vuelo247.prototype.R;
import com.vuelo247.tareas.TraerCanciones;


public class activity_home extends ActionBarActivity {

    RelativeLayout relative_eventos;
    RelativeLayout relative_multimedia;
    RelativeLayout relative_canciones;
    RelativeLayout relative_contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
		.discCacheExtraOptions(480, 800, CompressFormat.PNG, 75, null).build();
		
		ImageLoader.getInstance().init(config);
        levantarXML();
        levantarEventos();
    }

    private void levantarEventos() {

        relative_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_eventos = new Intent(activity_home.this,activity_eventos.class);
                startActivity(intent_eventos);
            }
        });

        relative_canciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_canciones = new Intent(activity_home.this,activity_canciones.class);
                startActivity(intent_canciones);
            }
        });

        relative_multimedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_multimedia = new Intent(activity_home.this,activity_multimedia.class);
                startActivity(intent_multimedia);
            }
        });
    }

    private void levantarXML() {

        relative_eventos = (RelativeLayout) findViewById(R.id.relative_eventos);
        relative_canciones = (RelativeLayout) findViewById(R.id.relative_canciones);
        relative_contacto = (RelativeLayout) findViewById(R.id.relative_contacto);
        relative_multimedia = (RelativeLayout) findViewById(R.id.relative_multimedia);
    }
}
