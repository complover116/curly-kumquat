package com.bitkeks.ckq;

public abstract class Entity {
	public abstract void tick(double delta);

	public abstract void draw();

	boolean isDead = false;

	public void remove() {
		this.isDead = true;
	}
}
