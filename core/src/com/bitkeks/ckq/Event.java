package com.bitkeks.ckq;

public class Event {
	//static final int TYPE_METALOBJECT = 1;
	
	float time;
	FObjectType object;
	float x;
	float y;
	int newLayer;
	
	boolean happened = false;
	public Event(FObjectType object, float time, float x, float y) {
		this.object = object;
		this.time = time;
		this.x = x;
		this.y = y; 
	}
}
