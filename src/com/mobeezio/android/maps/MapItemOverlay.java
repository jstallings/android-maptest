package com.mobeezio.android.maps;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public abstract class MapItemOverlay extends Overlay {
	private ArrayList<IMapItem> overlays = new ArrayList<IMapItem>();
	private final Drawable defaultMarker;

	public void addOverlay(IMapItem item) {
		overlays.add(item);
	}

	public MapItemOverlay(Drawable drawable) {
		this.defaultMarker = drawable;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);

		Projection proj = mapView.getProjection();
		Rect box = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());

		Iterator<IMapItem> it = overlays.iterator();
		Point point = new Point();
		while (it.hasNext()) {
			IMapItem o = it.next();
			proj.toPixels(o.getPoint(), point);
			if (box.contains(point.x, point.y)) {
				// draw it
				Drawable marker;
				if (o.getMarker() == null) {
					marker = this.defaultMarker;
				} else {
					marker = null; // o.getMarker();
				}

				// Set the bounds of the marker
				marker.setBounds(point.x - marker.getIntrinsicWidth() / 2,
				point.y - marker.getIntrinsicHeight() / 2, point.x
				+ marker.getIntrinsicWidth()/2,
				point.y + marker.getIntrinsicHeight()/2);
				marker.draw(canvas);
			}
		}
	}

	@Override
	public boolean onTap(GeoPoint point, MapView mapView) {
		Log.v("onTap", "tapping");
		Projection projection = mapView.getProjection();
		Point tapped = new Point();
		projection.toPixels(point, tapped);

		Point p = new Point();
		for (int i = 0; i < overlays.size(); i++) {
			IMapItem o = overlays.get(i);
			Drawable drawable;
			if (o.getMarker() == null) {
				drawable = this.defaultMarker;
			} else {
				drawable = o.getMarker();
			}
			projection.toPixels(o.getPoint(), p);
			Rect r = new Rect(p.x - drawable.getIntrinsicWidth() / 2, p.y
					- drawable.getIntrinsicHeight() / 2, p.x
					+ drawable.getIntrinsicWidth() / 2, p.y
					+ drawable.getIntrinsicHeight() / 2);
			if (r.contains(tapped.x, tapped.y)) {
				return onTap(o, mapView);
			}
		}

		return true;
	}

	abstract protected boolean onTap(IMapItem item, MapView mapView);
	

}
