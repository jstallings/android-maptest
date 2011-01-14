package com.mobeezio.android.maptest;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.mobeezio.android.maps.IMapItem;

public class TestItem implements IMapItem {
	private String title;
	private String description;
	private Drawable marker;
	private GeoPoint point;

	public TestItem(GeoPoint point,String title, String description) {
		this.title = title;
		this.description = description;
		this.point = point;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Drawable getMarker() {
		return marker;
	}

	public void setMarker(Drawable marker) {
		this.marker = marker;
	}

	public GeoPoint getPoint() {
		return point;
	}

	public void setPoint(GeoPoint point) {
		this.point = point;
	}

}
