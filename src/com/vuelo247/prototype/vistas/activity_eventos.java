package com.vuelo247.prototype.vistas;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.vuelo247.prototype.Evento;
import com.vuelo247.prototype.R;
import com.vuelo247.prototype.adaptadores.AdaptadorEventos;
import com.vuelo247.tareas.TraerEventos;

public class activity_eventos extends ActionBarActivity {

    Evento evento;

    ArrayList<Evento> eventos;

    AdaptadorEventos adaptadorEventos;

    ListView list_eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        new TraerEventos(activity_eventos.this).execute();
        
        list_eventos = (ListView) findViewById(R.id.list_eventos);
        
        list_eventos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Evento evento = (Evento) list_eventos.getAdapter().getItem(position);
				
				Intent intent = new Intent(getApplicationContext(), activity_detalle_evento.class);
				
				Bundle bundle = new Bundle();
				
				bundle.putString("nombre", evento.getNombre());
				bundle.putString("fecha_inicio", evento.getFecha_inicio());
				bundle.putString("fecha_final", evento.getFecha_final());
				bundle.putString("latitud", evento.getLatitud());
				bundle.putString("longitud", evento.getLongitud());
				bundle.putString("precio", evento.getPrecio());
				bundle.putString("direccion", evento.getDireccion());
				
				intent.putExtra("evento", bundle);
				
				startActivity(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
