package com.example.dragtest;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	private GridView gridView1;
	private float lastKnownY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gridView1 = (GridView) findViewById(R.id.gridView1);

		ArrayList<String> objects = new ArrayList<String>();
		for (int i = 0; i < 48; i++) {
			objects.add(String.valueOf(i));
		}

		final OnDragListener dragListener = new OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {
				// Log.v("GridAdapter","DragEvent");
				switch (event.getAction()) {
				case DragEvent.ACTION_DRAG_STARTED:
					// Log.v("GridAdapter", "drag started");
					break;
				case DragEvent.ACTION_DRAG_LOCATION:
					Log.v("GridAdapter", "drag location: " + v.getY() + " position: " + v.toString());
					lastKnownY = v.getY();
					break;
				case DragEvent.ACTION_DRAG_ENTERED:
					// Log.v("GridAdapter", "drag entered");
					v.setBackgroundColor(0xFFFF0000);
					break;
				case DragEvent.ACTION_DRAG_EXITED:
					// Log.v("GridAdapter", "drag exited with: " +
					// lastKnownY);
					v.setBackgroundColor(0xFF00FF00);
					if (lastKnownY >= 880) {
						scrollDown();

					} else if (lastKnownY <= 0) {
						scrollUp();
					}
					break;
				case DragEvent.ACTION_DROP:
					// Log.v("GridAdapter", "drop");
					// handle drop
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					// Log.v("GridAdapter", "drag ended");
					break;
				}
				return true;
			}
		};

		final GridAdapter adapter = new GridAdapter(this, 0, 0, objects, this, dragListener);
		gridView1.setAdapter(adapter);

	}

	public void scrollDown() {
		gridView1.smoothScrollByOffset(10);
	}

	public void scrollUp() {
		gridView1.smoothScrollByOffset(-10);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
