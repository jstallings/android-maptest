package com.mobeezio.android.maps;

import android.graphics.drawable.Drawable;
import com.google.android.maps.GeoPoint;

public interface IMapItem {

	public String getTitle();

	public String getDescription();

	public Drawable getMarker();

	public GeoPoint getPoint();

}
