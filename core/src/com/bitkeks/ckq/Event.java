package com.bitkeks.ckq;

public class Event {
	//static final int TYPE_METALOBJECT = 1;
	
	float time;
	Object object;
	int x;
	int y;
	int newLayer;
	
	boolean happened = false;
	public Event(Object object, float time, float x, float y) {
		this.object = object;
		this.time = time;
	}
}
