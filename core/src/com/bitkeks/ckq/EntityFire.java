package com.bitkeks.ckq;

import com.badlogic.gdx.graphics.Color;

public class EntityFire extends Entity {

	
	float lifetime, radius, particleSpawn;
	boolean real;
	
	@Override
	public void tick(double delta) {
		lifetime -= delta;
		particleSpawn += delta;
		if(x>CurGame.character.x + GameScreen.WIDTH) return;
		if(x<CurGame.character.x - GameScreen.WIDTH) return;
		if(y>CurGame.character.y + GameScreen.HEIGHT) return;
		if(y<CurGame.character.y - GameScreen.HEIGHT) return;
		if(real && Math.sqrt((CurGame.character.x-x)*(CurGame.character.x-x) +(CurGame.character.y-y)*(CurGame.character.y-y))< radius) {
			CurGame.character.health -= delta*50;
		}
		if(particleSpawn > 0.01){
		for(int i = 0; i < radius/32; i ++){
			CurGame.entities.add(new Particle((float) (x+Math.random()*radius-radius/2), (float) (y+Math.random()*radius-radius/2), (float)Math.random()*10, (float) (Math.random()*64+16), 6, 3, Color.YELLOW));
			CurGame.entities.add(new Particle((float) (x+Math.random()*radius-radius/2), (float) (y+Math.random()*radius-radius/2), (float)Math.random()*10, (float) (Math.random()*32+16), 6, 3, Color.ORANGE));
			CurGame.entities.add(new Particle((float) (x+Math.random()*radius-radius/2), (float) (y+Math.random()*radius-radius/2), (float)Math.random()*10, (float) (Math.random()*16), 6, 3, Color.RED));
		}
		particleSpawn = 0;
		}
		
		if(lifetime < 0){
			this.isDead = true;
			for(int i = 0; i < radius; i ++)
			CurGame.entities.add(new Particle((float) (x+Math.random()*radius-radius/2), (float) (y+Math.random()*radius-radius/2), 0, (float) (Math.random()*32+16), 6, 3, Color.GRAY));
		}
	}
	
	public EntityFire(float x, float y, float radius, float lifetime, boolean real) {
		this.x = x;
		this.y = y;
		this.real = real;
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
