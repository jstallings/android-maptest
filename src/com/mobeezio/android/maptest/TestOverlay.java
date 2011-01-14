package com.mobeezio.android.maptest;

import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.mobeezio.android.maps.IMapItem;
import com.mobeezio.android.maps.MapItemOverlay;

public class TestOverlay extends MapItemOverlay {

	public TestOverlay(Drawable drawable) {
		super(drawable);
	}

	@Override
	protected boolean onTap(IMapItem item, MapView mapView) {
		Toast.makeText(mapView.getContext(), item.getTitle(), 5).show();
		return true;
	}

}
