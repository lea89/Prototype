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
import android.widget.ListView;

import com.vuelo247.prototype.Evento;
import com.vuelo247.prototype.R;
import com.vuelo247.prototype.adaptadores.AdaptadorEventos;

public class TraerEventos extends AsyncTask<Void,Void,ArrayList<Evento>>{
	Activity activity;
	ProgressDialog pd;

	public TraerEventos(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		pd = new ProgressDialog(activity);
		pd.setMessage("Obteniendo eventos");
		pd.show();
		super.onPreExecute();
	}

	@Override
	protected ArrayList<Evento> doInBackground(Void... params) {
		ArrayList<Evento> list_eventos = new ArrayList<Evento>();

		try {
			StringBuilder builder = new StringBuilder();

			String URL = activity.getResources().getString(
					R.string.api_eventos);

			HttpClient client = new DefaultHttpClient();

			HttpGet httpGet = new HttpGet(URL);

			Log.d("Curso", URL);

			HttpResponse response = client.execute(httpGet);

			StatusLine statusLine = response.getStatusLine();

			int statusCode = statusLine.getStatusCode();

			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();

				InputStream content = entity.getContent();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));

				String line;

				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}

				if (builder.toString().length() > 0) {
					JSONObject jsonObject = new JSONObject(builder.toString());

					JSONArray eventosArray = jsonObject
							.getJSONArray("eventos");

					for (int i = 0; i < eventosArray.length(); i++) {
						JSONObject eventoJSON = eventosArray
								.getJSONObject(i);
						Evento oEvento = new Evento();
						oEvento.setNombre(eventoJSON.getString("nombre"));
						oEvento.setLatitud(eventoJSON.getString("latitud"));
						oEvento.setLongitud(eventoJSON.getString("longitud"));
						oEvento.setFecha_final(eventoJSON.getString("fecha_final"));
						oEvento.setFecha_inicio(eventoJSON.getString("fecha_inicio"));
						oEvento.setPrecio(eventoJSON.getString("precio"));
						oEvento.setDireccion(eventoJSON.getString("direccion"));
						list_eventos.add(oEvento);
					}
				} else {
					Log.e("Curso", "Error");
				}
			} else {
				Log.e("Curso", "Error SL " + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Curso", "Error");
		}
		return list_eventos;
	}

	@Override
	public void onPostExecute(ArrayList<Evento> result) {
		pd.dismiss();

		AdaptadorEventos adaptador = new AdaptadorEventos(activity, result);

//		ActionSlideExpandableListView list_eventos = (ActionSlideExpandableListView) activity
//				.findViewById(R.id.list_eventos);
		ListView list_eventos = (ListView) activity.findViewById(R.id.list_eventos);

		list_eventos.setAdapter(adaptador);
	}

}
