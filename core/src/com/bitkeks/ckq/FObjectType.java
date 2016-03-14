package com.bitkeks.ckq;

import java.util.ArrayList;

public class FObjectType {

	static ArrayList<FObjectType> objects = new ArrayList<FObjectType>();

	static final int TYPE_WOOD = 0;
	static final int TYPE_METAL = 1;

	int type;
	String imagename;
	int radius;

	public FObjectType(int type, String imagename, int radius) {
		this.imagename = imagename;
		this.radius = radius;
		this.type = type;
	}
}
