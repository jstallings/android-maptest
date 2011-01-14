package com.mobeezio.android.maptest;

import java.util.Random;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.mobeezio.android.maps.MapItemOverlay;

public class MapTestActivity extends MapActivity {
	/** Called when the activity is first created. */
	private final RandomGenerator random = new RandomGenerator();

	private GeoPoint getRandomPoint() {
		return new GeoPoint(random.next((int) (-80.0f * 1E6),
				(int) (80.0f * 1E6)), random.next((int) (-180.0f * 1E6),
				(int) (180.0f * 1E6)));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		MapView mapView = (MapView) findViewById(R.id.myMap);
		mapView.setBuiltInZoomControls(true);
		
		Drawable drawable = getResources().getDrawable(R.drawable.androidmarker);
		TestOverlay overlay = new TestOverlay(drawable);
		
		// Generate a list of Random Points
		for (int i = 0; i < 500; i++) {
			GeoPoint point = getRandomPoint();
			TestItem item = new TestItem(point,String.format("Item %d",
					i), "Description");
			overlay.addOverlay(item);
		}
		
		mapView.getOverlays().add(overlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	private class RandomGenerator extends Random {
		public int next(int lower, int higher) {
			return nextInt(higher - lower + 1) + lower;
		}
	}
}