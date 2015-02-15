package com.vuelo247.tareas;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.vuelo247.prototype.Cancion;
import com.vuelo247.prototype.R;
import com.vuelo247.prototype.adaptadores.AdaptadorCanciones;
import com.vuelo247.prototype.librerias.ActionSlideExpandableListView;

public class TraerCanciones extends AsyncTask<Void,Void,ArrayList<Cancion>>{

	Activity activity;
	ProgressDialog pd; 
	
	public TraerCanciones(Activity activity)
	{
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute()
	{
		pd = new ProgressDialog(activity);
		pd.show();
		super.onPreExecute();
	}
	
	@Override
	protected ArrayList<Cancion> doInBackground(Void... params) 
	{
		ArrayList<Cancion> list_canciones = new ArrayList<Cancion>();
		
		try 
 		{
	 		StringBuilder builder = new StringBuilder();
	 
	 		String URL = activity.getResources().getString(R.string.api_canciones);
	 		
	 		HttpClient client = new DefaultHttpClient();
	 		
	 		HttpGet httpGet = new HttpGet(URL);
	 		
	 		Log.d("Curso", URL);	 		
 			
	 		HttpResponse response = client.execute(httpGet);
 			
	 		StatusLine statusLine = response.getStatusLine();
 			
	 		int statusCode = statusLine.getStatusCode();
 			
	 		if (statusCode == 200) 
 			{	
 				HttpEntity entity = response.getEntity();
 				
 				InputStream content = entity.getContent();
 				
 				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
 				
 				String line;
 				
 				while ((line = reader.readLine()) != null) 
 				{
 						builder.append(line);
 				}
 	 			
 	 	    	if (builder.toString().length()>0)
 	 	    	{    		 	    			
 	 	    		JSONObject jsonObject = new JSONObject(builder.toString());  	    		 	
 	 	    		
 	 	    		JSONArray cancionesArray = jsonObject.getJSONArray("canciones");
 	 	    		
 	 	    		Log.d("Curso", "Cantidad de esferas: "+cancionesArray.length());
 	 	    		Log.d("Curso",cancionesArray.get(0)+"");
 	 	    		for (int i=0;i<cancionesArray.length();i++)
 	 	    		{
 	 	    			JSONObject cancionJSON = cancionesArray.getJSONObject(i);
 	 	    			Cancion oCancion = new Cancion();
 	 	    			oCancion.setId_cancion(cancionJSON.getInt("id_cancion"));
 	 	    			oCancion.setNombre(cancionJSON.getString("nombre"));
 	 	    			oCancion.setDuracion(cancionJSON.getString("duracion"));
 	 	    			oCancion.setAlbum(cancionJSON.getString("album"));
 	 	    			oCancion.setPath(cancionJSON.getString("path"));
 	 	    			list_canciones.add(oCancion);
 	 	    		}
 	 	   		}
 	 	    	else
 	 	    	{
 	 	    		Log.e("Curso", "Error");
 	 	    	}
 			}
 			else
 			{
 				Log.e("Curso", "Error SL "+statusCode);
 			}
 		}
 	    catch(Exception e)
 	    {
 	    	e.printStackTrace(); 	          
 	    	Log.e("Curso", "Error");
 	    }
		return list_canciones;
	}
	
	@Override
	public void onPostExecute(ArrayList<Cancion> result)
	{
		pd.dismiss();
        
		AdaptadorCanciones adaptador = new AdaptadorCanciones(activity, result);
		
		ActionSlideExpandableListView list_canciones = (ActionSlideExpandableListView) activity.findViewById(R.id.list_canciones);
		
		list_canciones.setAdapter(adaptador);
	}
	
}
