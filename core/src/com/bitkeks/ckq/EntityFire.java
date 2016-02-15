package com.bitkeks.ckq;

import com.badlogic.gdx.graphics.Color;

public class EntityFire extends Entity {

	
	float lifetime, radius;
	
	@Override
	public void tick(double delta) {
		lifetime -= delta;
		CurGame.entities.add(new Particle((float) (x+Math.random()*radius-radius/2), (float) (y+Math.random()*radius-radius/2), 0, (float) (Math.random()*32+16), 6, 3, Color.ORANGE));
		if(lifetime < 0){
			this.isDead = true;
			for(int i = 0; i < 20; i ++)
			CurGame.entities.add(new Particle((float) (x+Math.random()*radius-radius/2), (float) (y+Math.random()*radius-radius/2), 0, (float) (Math.random()*32+16), 6, 3, Color.GRAY));
		}
			
	}
	
	public EntityFire(float x, float y, float radius, float lifetime) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.lifetime = lifetime;
	}

	@Override
	public void draw() {
		float RDeviation = (float) (Math.random()*radius/10);
		KumQuat.shapeRenderer.setColor(1, 0.6f, 0, 0.2f);
		KumQuat.shapeRenderer.circle(x, y, radius+RDeviation);	
		KumQuat.shapeRenderer.setColor(1, 1, 0, 0.2f);
		KumQuat.shapeRenderer.circle(x, y, radius*8/10+RDeviation);
	}

}
