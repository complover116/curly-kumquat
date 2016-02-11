package com.bitkeks.ckq;

import com.badlogic.gdx.Gdx;

public class Character {
	float x;
	float y;
	float rot;
	float walkspeed = 0;
	float goalTurn = 0;
	float dx = 0;
	float dy = 0;
	float anim = 0;
	public Character() {
		x = 40;
		y = 40;
	}
	
	boolean checkMove(float x, float y) {
		for(int i = (int) (x/32 - 2); i < x/32 + 2; i ++) {
			for(int j = (int) (y/32 - 2); j < y/32 + 2; j ++) {
				try{
				if(CurGame.maze.tiles[CurGame.curLayer][i][j] != 0) {
					if(x < i*32+16&&x >= i*32-16&&y < j*32+16&&y>=j*32-16) {
						return false;
					}else {
						
					}
				}
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
		return true;
	}
	public void draw() {
		if(anim == 0)
			KumQuat.batch.draw(Resources.getImage("character/new"), x, y, 15.5f, 15.5f, 32, 32, 1,1, -rot);
		if(anim > 0)
			KumQuat.batch.draw(Resources.getImage("character/new_walk1"), x, y, 15.5f, 15.5f, 32, 32, 1,1, -rot);
		if(anim < 0)
			KumQuat.batch.draw(Resources.getImage("character/new_walk2"), x, y, 15.5f, 15.5f, 32, 32, 1,1, -rot);
	}
	public void tick(float deltaT) {
		if(goalTurn-rot>180)
			rot += 360;
		if(goalTurn-rot<-180)
			rot -= 360;
		
			rot += (goalTurn-rot)*deltaT*10;
		
		dx = x + (float) (Math.sin(Math.toRadians(rot))*walkspeed*deltaT);
		dy = y + (float) (Math.cos(Math.toRadians(rot))*walkspeed*deltaT);
		
		if(walkspeed == 0) {
			anim = 0;
		}
		anim += walkspeed/32*deltaT;
		
		if(anim > 1) {
			anim -= 3;
			Resources.playSound("step/step_"+(int)(Math.random()*3+1));
		} else {
			if(anim > -1 && anim < 0) {
				anim += 1;
				Resources.playSound("step/step_"+(int)(Math.random()*3+1));
			}
		}
		
		if(checkMove(dx,dy)) {
			y = dy;
			x = dx;
		} else if(checkMove(x,dy)){
			y = dy;
		} else if(checkMove(dx,y)){
			x = dx;
		}
	}
}
