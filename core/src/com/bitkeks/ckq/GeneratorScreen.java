package com.bitkeks.ckq;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GeneratorScreen implements Screen {
	public static Maze maze = new Maze();
	static double progress = 0;
	static int cellsProcessed = 0;
	static Coords curCell = new Coords(0,0);
	static Coords grid[][];
	static boolean brk;
	
	static class Coords {
		int x;
		int y;
		boolean visited = false;
		public Coords(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static ArrayList<Coords> trace = new ArrayList<Coords>();
	
	
	float WIDTH;
	float HEIGHT;
	float Xtension;
	
	public GeneratorScreen() {
		grid = new Coords[maze.cellsX][maze.cellsY];
		for(int i = 0; i < maze.cellsX; i ++)
			for(int j = 0; j < maze.cellsX; j ++)
				grid[i][j] = new Coords(i, j);
		maze.tiles = new int[maze.layernum][maze.cellsX*2 + 1][maze.cellsY*2 + 1];
		maze.caheIDs = new int[maze.layernum];
		for(int i = 0; i < maze.cellsX*2 + 1; i++)
			for(int j = 0; j < maze.cellsY*2 + 1; j++)
				maze.tiles[0][i][j] = -1;
				
		for(int i = 0; i < maze.cellsX; i++)
			for(int j = 0; j < maze.cellsY; j++)
				maze.tiles[0][i*2+1][j*2+1] = 0;
	}
	public static void connectTiles() {
	byte up = 0;
	byte down = 0;
	byte left = 0;
	byte right = 0;
	
		for(int i = 0; i < maze.tiles[0].length; i ++)
			for(int j = 0; j < maze.tiles[0][0].length; j ++)
			if(maze.tiles[0][i][j] == -1){
				up = 0;
				right = 0;
				down = 0;
				left = 0;
				
				try{
					if(maze.tiles[0][i-1][j] != 0)
						left = 1;
				} catch(ArrayIndexOutOfBoundsException e){}
				try{
					if(maze.tiles[0][i+1][j] != 0)
						right = 1;
				} catch(ArrayIndexOutOfBoundsException e){}
				try{
					if(maze.tiles[0][i][j-1] != 0)
						down = 1;
				} catch(ArrayIndexOutOfBoundsException e){}
				try{
					if(maze.tiles[0][i][j+1] != 0)
						up = 1;
				} catch(ArrayIndexOutOfBoundsException e){}
				maze.tiles[0][i][j] = up+right*2+down*4+left*8;
			}
	}
	public static void cacheLayer(int layer) {
		byte up = 0;
		byte down = 0;
		byte left = 0;
		byte right = 0;
		KumQuat.cache.beginCache();
		Gdx.app.log("Generator", "Caching layer "+layer);
		for(int i = 0; i < GeneratorScreen.maze.tiles[0].length; i ++){
			
			for(int j = 0; j < GeneratorScreen.maze.tiles[0][0].length; j ++)
				{
					//KumQuat.cache.getTransformMatrix().setToTranslation(i*32, j*32, 0);
					KumQuat.cache.add(Resources.getImage("tiles/floor-1"), i*32, j*32);
					if(GeneratorScreen.maze.tiles[layer][i][j] != 0){
						
						up = 0;
						right = 0;
						down = 0;
						left = 0;
						
						try{
							if(maze.tiles[0][i-1][j] != 0)
								left = 1;
						} catch(ArrayIndexOutOfBoundsException e){}
						try{
							if(maze.tiles[0][i+1][j] != 0)
								right = 1;
						} catch(ArrayIndexOutOfBoundsException e){}
						try{
							if(maze.tiles[0][i][j-1] != 0)
								down = 1;
						} catch(ArrayIndexOutOfBoundsException e){}
						try{
							if(maze.tiles[0][i][j+1] != 0)
								up = 1;
						} catch(ArrayIndexOutOfBoundsException e){}
						
						KumQuat.cache.add(Resources.getImage("tiles/wall-"+up+"-"+right+"-"+down+"-"+left+"-bck"), i*32, j*32);
					}
				}
		}
		
		maze.caheIDs[layer] = KumQuat.cache.endCache();
	}
	public static void tickGenerator() {
		if(!curCell.visited){
			curCell.visited = true;
			cellsProcessed++;
			progress = (double)cellsProcessed/((double)maze.cellsX*(double)maze.cellsY)*100;
		}
		ArrayList<Coords> possiblePaths = new ArrayList<Coords>();
		try{
			if(!grid[curCell.x-1][curCell.y].visited)
				possiblePaths.add(grid[curCell.x-1][curCell.y]);
		} catch(Exception e) {}
		try{
			if(!grid[curCell.x+1][curCell.y].visited)
				possiblePaths.add(grid[curCell.x+1][curCell.y]);
		} catch(Exception e) {}
		try{
			if(!grid[curCell.x][curCell.y-1].visited)
				possiblePaths.add(grid[curCell.x][curCell.y-1]);
		} catch(Exception e) {}
		try{
			if(!grid[curCell.x][curCell.y+1].visited)
				possiblePaths.add(grid[curCell.x][curCell.y+1]);
		} catch(Exception e) {}
		
		if(possiblePaths.size() == 0) {
			//BACKTRACK
			if(trace.size() == 0) {
				for(int i = 0; i < maze.cellsX*maze.cellsY/10; i++) {
					maze.tiles[0][2*(int)(Math.random()*maze.cellsX) + 1][2*(int)(Math.random()*maze.cellsY) + 1] = 0;
				}
				Gdx.app.log("Generator", "Connecting tiles...");
				//connectTiles();
				
				cacheLayer(0);
				
				brk = true;
				CurGame.maze = maze;
				KumQuat.game.setScreen(KumQuat.GMS);
			} else {
				curCell = trace.get(trace.size()-1);
				trace.remove(trace.size()-1);
				//Gdx.app.log("Generator", "Backtracking "+curCell.x+";"+curCell.y);
				tickGenerator();
			}
		} else {
			Coords nextCell = possiblePaths.get((int)(possiblePaths.size()*Math.random()));
			trace.add(curCell);
			maze.tiles[0][((curCell.x*2)+2+(nextCell.x*2))/2][((curCell.y*2)+2+(nextCell.y*2))/2] = 0;
			curCell = nextCell;
			//Gdx.app.log("Generator", "Going to "+curCell.x+";"+curCell.y);
		}
	}
	@Override
	public void render(float delta) {
	for(int i = 0; i < 40; i ++){
		tickGenerator();
		if(brk){brk = false; break;}
	}
		
		
		//float leftGutter = KumQuat.viewport.getLeftGutterWidth();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		KumQuat.camera.update();
		KumQuat.batch.setProjectionMatrix(KumQuat.camera.combined);
		KumQuat.batch.begin();
		
		for(int i = 0; i < 20; i ++) {
			if(progress>i*5) {
				KumQuat.batch.draw(Resources.getImage("interface/progressbar/progress5"), 50+i*35, 100);
			}
		}
		KumQuat.batch.draw(Resources.getImage("interface/progressbar/progressbarback"), 50, 100);
		KumQuat.batch.end();
	}
	@Override
	public void pause() {
	
	}
	@Override
	public void resume() {
	
	}
	@Override
	public void show() {

	}
	@Override
	public void resize(int width, int height) {
		KumQuat.viewport.update(width, height);
		WIDTH = KumQuat.viewport.getWorldWidth();
		HEIGHT = KumQuat.viewport.getWorldHeight();
		Xtension = (WIDTH-800)/2;
	}
	
	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}