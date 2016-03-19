package com.bitkeks.ckq;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class CurGame {
	public static Maze maze;
	public static int curLayer = 0;
	public static Character character = new Character();
	static double gameTime = 0;

	static double lastEvent = 0;
	
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static boolean win;

	public static void tickEnts(double delta) {
		for (int i = entities.size() - 1; i > -1; i--){
			if(entities.get(i).isDead){
				entities.remove(i);
			} else 
				entities.get(i).tick(delta);
		}
		
		
	}

	public static void tickEvents(double delta) {
		for (int i = 0; i < maze.events.size(); i++) {
			if (maze.events.get(i).time < gameTime+3 && !maze.events.get(i).happened) {
				entities.add(new FallingObject(maze.events.get(i).x-64 ,maze.events.get(i).y-64, 3, maze.events.get(i).object));
				maze.events.get(i).happened = true;
			}
			if (maze.events.get(i).time < gameTime) {
				curLayer = maze.events.get(i).newLayer;
				Gdx.app.log("Event", "Pregenerated event at " + maze.events.get(i).time + " (+"
						+ Math.floor(100*(gameTime - maze.events.get(i).time))/100 + "s) newLayer:" + curLayer);
				
				maze.events.remove(i);
				break;
			}
		}
		
		lastEvent += delta;
		while(lastEvent > 1){
		if(Math.random() < (float)(MetaGame.level)*0.1) {
			float x = (float)(character.x - 500 + Math.random()*1000);
			float y = (float) (character.y - 500 + Math.random()*1000);
			if(x > 32 && y>32 && x < maze.tiles.length*32 && y < maze.tiles[0].length*32)
			entities.add(new FallingObject(x, y, 3, FObjectType.woodenObjects.get((int)(Math.random()*FObjectType.woodenObjects.size()))));
			
		}
		lastEvent -= 1;
		}
		
	}

	public static void reset() {
		curLayer = 0;
		gameTime = 0;
		lastEvent = 0;
		entities.clear();
		character = new Character();
	}
}
