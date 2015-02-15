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

import com.vuelo247.prototype.R;
import com.vuelo247.prototype.Video;
import com.vuelo247.prototype.adaptadores.AdaptadorVideos;

public class TraerVideos extends AsyncTask<Void,Void,ArrayList<Video>>{

	Activity activity;
	ProgressDialog pd; 
	
	public TraerVideos(Activity activity)
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
	protected ArrayList<Video> doInBackground(Void... params) 
	{
		ArrayList<Video> list_videos = new ArrayList<Video>();
		
		try 
 		{
	 		StringBuilder builder = new StringBuilder();
	 		
	 		
	 		HttpClient client = new DefaultHttpClient();
	 		
	 		String URL = activity.getResources().getString(R.string.api_videos);
	 		
	 		HttpGet httpGet = new HttpGet(URL);
	 		
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
 	 	    		
 	 	    		JSONArray videosArray = jsonObject.getJSONArray("videos");
 	 	    		
 	 	    		for (int i=0;i<videosArray.length();i++)
 	 	    		{
 	 	    			JSONObject videoJSON = videosArray.getJSONObject(i);
 	 	    			Video oVideo = new Video();
 	 	    			oVideo.setNombre(videoJSON.getString("nombre"));
 	 	    			oVideo.setDuracion(videoJSON.getString("duracion"));
 	 	    			oVideo.setAlbum(videoJSON.getString("album"));
 	 	    			oVideo.setPath(videoJSON.getString("path"));
 	 	    			list_videos.add(oVideo);
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
		return list_videos;
	}
	
	@Override
	public void onPostExecute(ArrayList<Video> result)
	{
		pd.dismiss();
        
		AdaptadorVideos adaptador = new AdaptadorVideos(activity, result);
		
		ListView list_videos = (ListView) activity.findViewById(R.id.list_videos);
		
		list_videos.setAdapter(adaptador);
	}

}
