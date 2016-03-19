package com.bitkeks.ckq;

public class EntityExit extends Entity {

	boolean active = false;
	@Override
	public void tick(double delta) {
		if(!active) {
			if(Math.sqrt((CurGame.character.x - x+16)*(CurGame.character.x - x+16) + (CurGame.character.y - y+16)*(CurGame.character.y - y+16)) < 25) {
				this.active = true;
				CurGame.win = true;
			}
		}
	}
	
	public EntityExit(int x, int y) {
		this.x = x*32;
		this.y = y*32;
	}
	
	@Override
	public void drawBatch() {
		KumQuat.batch.draw(Resources.getImage("tiles/exit"), x, y);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
