package com.vuelo247.prototype.vistas;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vuelo247.prototype.Lugar;
import com.vuelo247.prototype.R;
import com.vuelo247.prototype.adaptadores.AdaptadorLugares;
import com.vuelo247.prototype.librerias.ActionSlideExpandableListView;

public class activity_detalle_evento extends ActionBarActivity {

    GoogleMap map;
    
    Lugar lugar;
    
    ArrayList<Lugar> lugares;
    
    Button btn_expandible;
    
    /*Evento*/
    TextView txt_nombre_evento;
    TextView txt_fecha_inicio_evento;
    TextView txt_fecha_fin_evento;
    TextView txt_precio_evento;
    
    /*Lugar*/
    TextView txt_direccion_evento;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_detalle_evento);
        
        levantarXML();
        btn_expandible = (Button) findViewById(R.id.expandable_toggle_button);
        
        Intent intent = getIntent();
        
        lugares = new ArrayList<Lugar>();
        lugar = new Lugar();
        
        lugar.setDireccion("Calle falsa 123");
        lugar.setNombre("Casa Rock");
        lugar.setMapUrl("http://maps.googleapis.com/maps/api/staticmap?center=-38.006473,-57.552545&zoom=16&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:C%7C-38.006473,-57.552545&sensor=true");
        
        lugares.add(lugar);
        
        lugar = new Lugar();
        
        lugar.setDireccion("Calle falsa 123");
        lugar.setNombre("Casa Rock");
        lugar.setMapUrl("http://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=15&size=600x300&maptype=roadmap"+
        		"&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318"+
        		"&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false");
        
        lugares.add(lugar);
        
        lugar = new Lugar();
        
        lugar.setDireccion("Calle falsa 123");
        lugar.setNombre("Casa Rock");
        lugar.setMapUrl("http://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=15&size=600x300&maptype=roadmap"+
        		"&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318"+
        		"&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false");
        
        lugares.add(lugar);
        
        lugar = new Lugar();
        
        lugar.setDireccion("Calle falsa 123");
        lugar.setNombre("Casa Rock");
        lugar.setMapUrl("http://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=15&size=600x300&maptype=roadmap"+
        		"&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318"+
        		"&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false");
        
        lugares.add(lugar);
        
        lugar = new Lugar();
        
        lugar.setDireccion("Calle falsa 123");
        lugar.setNombre("Casa Rock");
        lugar.setMapUrl("http://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=15&size=600x300&maptype=roadmap"+
        		"&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318"+
        		"&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false");
        
        lugares.add(lugar);
        
        FragmentManager fm = getSupportFragmentManager();
        AdaptadorLugares adaptador = new AdaptadorLugares(activity_detalle_evento.this,lugares,fm);
        
        ActionSlideExpandableListView list = (ActionSlideExpandableListView) findViewById(R.id.list);

        
        list.setAdapter(adaptador);

        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tab_evento = tabHost.newTabSpec("Evento");
        tab_evento.setContent(R.id.tab_evento);
        tab_evento.setIndicator("Evento");
        tabHost.addTab(tab_evento);

        TabHost.TabSpec tab_donde = tabHost.newTabSpec("Lugar");
        tab_donde.setContent(R.id.tab_evento_lugar);
        tab_donde.setIndicator("Lugar");
        tabHost.addTab(tab_donde);

        TabHost.TabSpec tab_conseguir_entrada = tabHost.newTabSpec("Entradas");
        tab_conseguir_entrada.setContent(R.id.tab_conseguir_entradas);
        tab_conseguir_entrada.setIndicator("Entradas");
        tabHost.addTab(tab_conseguir_entrada);

        tabHost.setCurrentTab(0);
        
        /*Evento*/
        txt_nombre_evento.setText(intent.getExtras().getBundle("evento").getString("nombre"));
        txt_fecha_inicio_evento.setText(intent.getExtras().getBundle("evento").getString("fecha_inicio"));
        txt_fecha_fin_evento.setText(intent.getExtras().getBundle("evento").getString("fecha_final"));
        txt_precio_evento.setText(intent.getExtras().getBundle("evento").getString("precio"));
        
        /*Lugar*/
        txt_direccion_evento.setText(intent.getExtras().getBundle("evento").getString("direccion"));
        MarkerOptions op = new MarkerOptions();
        
        double latitud = Double.parseDouble(intent.getExtras().getBundle("evento").getString("latitud"));
        double longitud = Double.parseDouble(intent.getExtras().getBundle("evento").getString("longitud"));
        
        LatLng marker = new LatLng(latitud,longitud);
        map.addMarker(op.position(marker));
        
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        map.moveCamera(CameraUpdateFactory.newLatLng(marker));
        map.animateCamera(zoom);
        
    }


    private void levantarXML()
    {
    	txt_nombre_evento = (TextView) findViewById(R.id.txt_nombre_evento);
    	txt_fecha_inicio_evento = (TextView) findViewById(R.id.txt_fecha_inicio_evento);
    	txt_fecha_fin_evento = (TextView) findViewById(R.id.txt_fecha_fin_evento);
    	txt_precio_evento = (TextView) findViewById(R.id.txt_precio_evento);
    	
    	txt_direccion_evento = (TextView) findViewById(R.id.txt_direccion_evento);
    	map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView)).getMap();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detalle_evento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
