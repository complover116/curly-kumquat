package com.bitkeks.ckq;

import com.badlogic.gdx.graphics.Color;

public class EntityFire extends Entity {

	
	float lifetime, radius;
	
	@Override
	public void tick(double delta) {
		lifetime -= delta;
		if(x>CurGame.character.x + GameScreen.WIDTH/2+radius) return;
		if(x<CurGame.character.x - GameScreen.WIDTH/2-radius) return;
		if(y>CurGame.character.y + GameScreen.HEIGHT/2+radius) return;
		if(y<CurGame.character.y - GameScreen.HEIGHT/2-radius) return;
		for(int i = 0; i < radius/32; i ++){
			CurGame.entities.add(new Particle((float) (x+Math.random()*radius-radius/2), (float) (y+Math.random()*radius-radius/2), (float)Math.random()*10, (float) (Math.random()*64+16), 6, 3, Color.YELLOW));
			CurGame.entities.add(new Particle((float) (x+Math.random()*radius-radius/2), (float) (y+Math.random()*radius-radius/2), (float)Math.random()*10, (float) (Math.random()*32+16), 6, 3, Color.ORANGE));
			CurGame.entities.add(new Particle((float) (x+Math.random()*radius-radius/2), (float) (y+Math.random()*radius-radius/2), (float)Math.random()*10, (float) (Math.random()*16), 6, 3, Color.RED));
		}
		if(lifetime < 0){
			this.isDead = true;
			for(int i = 0; i < radius; i ++)
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
