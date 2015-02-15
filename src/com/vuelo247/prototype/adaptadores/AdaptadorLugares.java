package com.vuelo247.prototype.adaptadores;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.RectF;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.imagezoom.ImageAttacher;
import com.imagezoom.ImageAttacher.OnMatrixChangedListener;
import com.imagezoom.ImageAttacher.OnPhotoTapListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.vuelo247.prototype.Lugar;
import com.vuelo247.prototype.R;

public class AdaptadorLugares extends BaseAdapter{

	Context context;
	ArrayList<Lugar> lugares;
	FragmentManager fm;
	
	public AdaptadorLugares(Context context,ArrayList<Lugar> lugares,FragmentManager fm)
	{
		this.lugares = lugares;
		this.context = context;
		this.fm = fm;
	}
	@Override
	public int getCount() {
		return lugares.size();
	}

	@Override
	public Lugar getItem(int position) {
		return lugares.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	private class ViewHolder
	{
		TextView txt_lugar;
		TextView txt_direccion_lugar;
		ImageView map;
		Button btn_expandable;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Lugar lugar = getItem(position);
		ViewHolder holder;
		
		if(convertView == null)
		{
			holder = new ViewHolder();
			
			LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = li.inflate(R.layout.renglon_lugares,parent,false);
			
			holder.txt_direccion_lugar = (TextView) convertView.findViewById(R.id.txt_direccion_lugar);
			holder.txt_lugar = (TextView) convertView.findViewById(R.id.txt_lugar);
			
			holder.map = (ImageView) convertView.findViewById(R.id.map);
			usingSimpleImage(holder.map);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_direccion_lugar.setText(lugar.getDireccion());
		holder.txt_lugar.setText(lugar.getNombre());
		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.ic_launcher)
        .showImageForEmptyUri(R.drawable.ic_launcher)
        .showImageOnFail(R.drawable.ic_launcher)
        .cacheInMemory(true)
        .cacheOnDisc(false)
        .build();
		
		ImageLoader.getInstance().displayImage(lugar.getMapUrl(), holder.map,options);
		return convertView;
	}
	
	  public void usingSimpleImage(ImageView imageView) {
	        ImageAttacher mAttacher = new ImageAttacher(imageView);
	        ImageAttacher.MAX_ZOOM = 2.0f; // Double the current Size
	        ImageAttacher.MIN_ZOOM = 1f; // Half the current Size
	        MatrixChangeListener mMaListener = new MatrixChangeListener();
	        mAttacher.setOnMatrixChangeListener(mMaListener);
	        PhotoTapListener mPhotoTap = new PhotoTapListener();
	        mAttacher.setOnPhotoTapListener(mPhotoTap);
	    }

	    private class PhotoTapListener implements OnPhotoTapListener {

	        @Override
	        public void onPhotoTap(View view, float x, float y) {
	        }
	    }

	    private class MatrixChangeListener implements OnMatrixChangedListener {

	        @Override
	        public void onMatrixChanged(RectF rect) {

	        }
	    }
	    
	    

}
