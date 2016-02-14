package com.bitkeks.ckq;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.Gdx;

public class CurGame {
	public static Maze maze;
	public static int curLayer = 0;
	public static Character character = new Character();
	static double gameTime = 0;

	public static ArrayList<Entity> entities = new ArrayList<Entity>();

	public static void tickEnts(double delta) {
		for (int i = entities.size() - 1; i > -1; i--){
			if(entities.get(i).isDead){
				entities.remove(i);
			} else 
				entities.get(i).tick(delta);
		}
			
	}

	public static void tickEvents() {
		for (int i = 0; i < maze.events.size(); i++) {
			if (maze.events.get(i).time < gameTime) {
				curLayer = maze.events.get(i).newLayer;
				
				float x0 = maze.events.get(i).x-96;
				float y0 = maze.events.get(i).y-96;
				float d = 96/5;
				Gdx.app.log("Event", "Pregenerated event at " + maze.events.get(i).time + " (+"
						+ Math.floor(100*(gameTime - maze.events.get(i).time))/100 + "s) newLayer:" + curLayer);
				//Gdx.app.log("Event", "Particle location:"+x0+":"+y0);
				for(int x = 0; x < 10; x ++)
					for(int y = 0; y < 10; y ++){
						entities.add(new Particle(x0+d*x, y0+d*y, (float)Math.random()*40-20, (float) (Math.random()*40-20), 20, 5, Color.GRAY));
					}
				maze.events.remove(i);
				break;
			}
		}
	}
}
