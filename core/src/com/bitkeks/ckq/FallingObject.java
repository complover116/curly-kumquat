package com.bitkeks.ckq;

public class FallingObject extends Entity {

	float x,y,rot,velX,velY,velRot,height;
	Object obj;
	@Override
	public void tick(double delta) {
		x += velX*delta;
		y += velY*delta;
		rot += velRot*delta;
		height -= delta;
		if(height < 0){
			this.isDead = true;
			Resources.playSound("env/crash"+(int)(Math.random()*2+1));
		}
	}

	public FallingObject(float x, float y, float delay, Object obj) {
		this.obj = obj;
		this.x = (float) (x + Math.random()*400-200);
		this.y = (float) (y + Math.random()*400-200);
		this.velX = (x - this.x)/delay;
		this.velY = (y - this.y)/delay;
		this.velRot = (float) (Math.random()*180-90);
		this.height = delay;
	}

	@Override
	public void drawBatch() {
		KumQuat.batch.draw(Resources.getImage("wreck/test_1"), x, y, 64, 64, 128, 128, height+1, height+1, rot);
	}

	@Override
	public void draw() {
	}

}
