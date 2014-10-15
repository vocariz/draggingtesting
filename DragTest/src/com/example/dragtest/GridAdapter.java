package com.example.dragtest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GridAdapter extends ArrayAdapter<String> {

	private Context ctx;
	private ArrayList<String> objetos;
	private MainActivity act;
	private boolean exiting;
	private float lastKnownY;
	private int pos;
	private OnDragListener dragListener;

	public GridAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> objects, MainActivity act,OnDragListener dragListener) {
		super(context, resource, textViewResourceId, objects);
		this.ctx = context;
		this.objetos = objects;
		this.act = act;
		this.dragListener = dragListener;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			Log.d("Creando holder", "Nuevo holder experienciasadapter");
			convertView = act.getLayoutInflater().inflate(R.layout.item_grid, null);
			holder = new ViewHolder();
			holder.iv = (TextView) convertView.findViewById(R.id.ivGrid);
			holder.iv.setText(objetos.get(position));
			pos = position;
			if(pos == 36 || pos == 37){
				convertView.setBackgroundColor(0xFF0000FF);
			}

			convertView.setTag(holder);
			final View theView = convertView;
			theView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					ClipData.Item item = new ClipData.Item(String.valueOf(position));

					String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
					ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);

					// Instantiates the drag shadow builder.
					View.DragShadowBuilder myShadow = new DragShadowBuilder(theView);

					// Starts the drag
					v.startDrag(dragData, // the data to be dragged
							myShadow, // the drag shadow builder
							null, // no need to use local data
							0 // flags (not currently used, set to 0)
					);
					return true;
				}
			});

			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		convertView.setOnDragListener(dragListener);
		
		
		
		return convertView;
	}

	static class ViewHolder {
		TextView iv;
	}

}
