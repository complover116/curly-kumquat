package com.bitkeks.ckq;

import com.badlogic.gdx.Gdx;

public class CurGame {
	public static Maze maze;
	public static int curLayer = 0;
	public static Character character = new Character();
	static double gameTime = 0;
	
	
	public static void tickEvents() {
		for(int i = 0; i < maze.events.size(); i ++) {
			if(maze.events.get(i).time < gameTime) {
				curLayer = maze.events.get(i).newLayer;
				Gdx.app.log("Event", "Pregenerated event at "+maze.events.get(i).time+ " (+" + (gameTime-maze.events.get(i).time)+"s) newLayer:"+curLayer);
				maze.events.remove(i);
				break;
			}
		}
	}
}
