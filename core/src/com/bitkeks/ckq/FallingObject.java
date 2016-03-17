package com.bitkeks.ckq;

import com.badlogic.gdx.graphics.Color;

public class FallingObject extends Entity {

	float rot,velX,velY,velRot,height;
	FObjectType obj;
	EntityFire fireEffect;
	@Override
	public void tick(double delta) {
		x += velX*delta;
		y += velY*delta;
		fireEffect.x = x+obj.radius;
		fireEffect.y = y+obj.radius;
		fireEffect.radius = obj.radius*(height+1);
		rot += velRot*delta;
		height -= delta;
		if(height < 0){
			this.isDead = true;
			fireEffect.isDead = true;
			
			if(Math.sqrt((CurGame.character.x-x-obj.radius)*(CurGame.character.x-x-obj.radius) + (CurGame.character.y-y-obj.radius)*(CurGame.character.y-y-obj.radius))< obj.radius*1.5) {
				CurGame.character.health = 0;
			}
			
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
			if(this.obj.type == FObjectType.TYPE_METAL)
			CurGame.entities.add(new EntityFire(x+obj.radius, y+obj.radius, obj.radius*2, 15, true));
			else
			CurGame.entities.add(new EntityFire(x+obj.radius, y+obj.radius, obj.radius*2, 5, true));
			//Gdx.app.log("Event", "Particle location:"+x0+":"+y0);
			for(int dx = 0; dx < obj.radius/4; dx ++)
				for(int dy = 0; dy < obj.radius/4; dy ++){
					CurGame.entities.add(new Particle(x + dx*8, y+dy*8, (float)Math.random()*80-40, (float) (Math.random()*80-40), 20, 5, Color.GRAY));
				}
		}
	}

	public FallingObject(float x, float y, float delay, FObjectType obj) {
		this.obj = obj;
		this.x = (float) (x + Math.random()*400-200);
		this.y = (float) (y + Math.random()*400-200);
		this.velX = (x - this.x)/delay;
		this.velY = (y - this.y)/delay;
		this.velRot = (float) (Math.random()*180-90);
		this.height = delay;
		fireEffect = new EntityFire(x+obj.radius, y+obj.radius, obj.radius, 15, false);
		CurGame.entities.add(fireEffect);
	}

	@Override
	public void drawBatch() {
		KumQuat.batch.draw(Resources.getImage(obj.imagename), x, y, obj.radius, obj.radius, obj.radius*2, obj.radius*2, height+1, height+1, rot);
	}

	@Override
	public void draw() {
	}

}
