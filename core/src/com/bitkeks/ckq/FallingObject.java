package com.bitkeks.ckq;

public class FallingObject extends Entity {

	float rot,velX,velY,velRot,height;
	Object obj;
	EntityFire fireEffect;
	@Override
	public void tick(double delta) {
		x += velX*delta;
		y += velY*delta;
		fireEffect.x = x+64;
		fireEffect.y = y+64;
		fireEffect.radius = 64*(height+1);
		rot += velRot*delta;
		height -= delta;
		if(height < 0){
			this.isDead = true;
			fireEffect.isDead = true;
			
			float dist = (float) Math.sqrt(Math.pow(x - CurGame.character.x, 2) + Math.pow(y - CurGame.character.y, 2));
			
			if(dist < 512)
			Resources.playSound("env/crash1");
			else {
				if(dist < 1024){
					float pan = Math.min(Math.max((x-CurGame.character.x)/1024, -1), 1);
					Resources.sounds.get("env/crash1_med").play(dist/2048, 1, pan);
				} else {
					float pan = Math.min(Math.max((x-CurGame.character.x)/1024, -1), 1);
					Resources.sounds.get("env/crash1_distant").play(dist/2048, 1, pan);
				}
			}
			
			CurGame.entities.add(new EntityFire(x+64, y+64, 128, 15));
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
		fireEffect = new EntityFire(x+64, y+64, 64, 15);
		CurGame.entities.add(fireEffect);
	}

	@Override
	public void drawBatch() {
		KumQuat.batch.draw(Resources.getImage("wreck/test_1"), x, y, 64, 64, 128, 128, height+1, height+1, rot);
	}

	@Override
	public void draw() {
	}

}
