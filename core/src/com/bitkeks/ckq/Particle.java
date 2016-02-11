package com.bitkeks.ckq;

import com.badlogic.gdx.graphics.Color;

public class Particle extends Entity {

	float x, y, velX, velY, size, time, decay;
	Color color;

	public Particle(float x, float y, float velX, float velY, float size, float decay, Color color) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.size = size;
		this.decay = decay;
		this.color = color;
	}

	@Override
	public void tick(double delta) {
		x += velX * delta;
		y += velY * delta;
		size -= decay * delta;
		if (size < 0) {
			remove();
		}
	}

	@Override
	public void draw() {
		KumQuat.shapeRenderer.setColor(color);
		KumQuat.shapeRenderer.rect(x-size/2, y-size/2, size, size);

	}

}
