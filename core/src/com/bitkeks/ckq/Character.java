package com.bitkeks.ckq;

public class Character {
	float x;
	float y;
	float rot;
	float walkspeed = 0;
	float goalTurn = 0;
	float dx = 0;
	float dy = 0;
	public Character() {
		x = 40;
		y = 40;
	}
	
	boolean checkMove(float x, float y) {
		for(int i = (int) (dx/32 - 2); i < dx/32 + 2; i ++) {
			for(int j = (int) (dy/32 - 2); j < dy/32 + 2; j ++) {
				try{
				if(CurGame.maze.tiles[CurGame.curLayer][i][j] != 0) {
					if(dx < i*32+16&&dx >= i*32-16&&dy < j*32+16&&dy>=j*32-16) {
						return false;
					}else {
						
					}
				}
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
		return true;
	}
	public void tick(float deltaT) {
		if(goalTurn-rot>180)
			rot += 360;
		if(goalTurn-rot<-180)
			rot -= 360;
		
			rot += (goalTurn-rot)*deltaT*10;
		
		dx = x + (float) (Math.sin(Math.toRadians(rot))*walkspeed*deltaT);
		dy = y + (float) (Math.cos(Math.toRadians(rot))*walkspeed*deltaT);
		
		
		
		
		if(checkMove(dx,dy)) {
			x = dx;
			y = dy;
		} else if(checkMove(x, dy)) {
			y = dy;
		}
	}
}
