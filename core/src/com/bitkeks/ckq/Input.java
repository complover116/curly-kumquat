package com.bitkeks.ckq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Input {
	static float joyX = 10;
	static float joyY = 0;
	static boolean joyActive = false;
	static int joyTouchID = 1337;
	
	public static void processKeyInput() {
		boolean flag = false;
		if(Gdx.input.isKeyPressed(Keys.W)) {
			CurGame.character.goalTurn = 0;
			flag = true;
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			CurGame.character.goalTurn = 90;
			flag = true;
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			CurGame.character.goalTurn = 180;
			flag = true;
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			CurGame.character.goalTurn = 270;
			flag = true;
		}
		if(flag)
			CurGame.character.walkspeed = 96; else CurGame.character.walkspeed = 0;
	}
	public static void processInput() {
		if(!joyActive){
		for(int i = 0; i < 4; i++) {
			if(Gdx.input.isTouched(i)) {
				
				Vector2 pos = KumQuat.UIviewport.unproject(new Vector2(Gdx.input.getX(i), Gdx.input.getY(i)));
				//Gdx.app.log("Input", "Joy active"+pos.x);
				if(pos.dst(100, 150) < 30) {
					joyActive = true;
					joyTouchID = i;
					
					break;
				}
			}
		}
		}
		else {
			
			if(!Gdx.input.isTouched(joyTouchID)){
				joyActive = false;
				joyX = 0;
				joyY = 0;
				CurGame.character.walkspeed = 0;
			} else {
				Vector2 pos = KumQuat.UIviewport.unproject(new Vector2(Gdx.input.getX(joyTouchID), Gdx.input.getY(joyTouchID))).sub(100, 150);
				
				pos.limit(100);
				joyX = pos.x;
				joyY = pos.y;
				CurGame.character.walkspeed = pos.len();
				CurGame.character.goalTurn = -pos.angle()+90;
			}
			
		}
	}
}
