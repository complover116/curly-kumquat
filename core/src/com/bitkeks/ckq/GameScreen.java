package com.bitkeks.ckq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {

	float scrollX;
	float scrollY;
	
	float WIDTH;
	float HEIGHT;
	float Xtension;
		
	AndroidButton buttons[] = new AndroidButton[4];
	public GameScreen() {
		buttons[0] = new AndroidButton(new Rectangle(0, 0, 800, 200));
		buttons[1] = new AndroidButton(new Rectangle(0, 400, 800, 200));
		buttons[2] = new AndroidButton(new Rectangle(0, 200, 400, 200));
		buttons[3] = new AndroidButton(new Rectangle(400, 200, 400, 200));
	}
	@Override
	public void render(float delta) {
		
		
		for(int i = 0; i < buttons.length; i ++) {
			buttons[i].update();
		}
		if(buttons[0].isPressed) scrollY += delta*1024;
		if(buttons[1].isPressed) scrollY -= delta*1024;
		if(buttons[2].isPressed) scrollX += delta*1024;
		if(buttons[3].isPressed) scrollX -= delta*1024;
		//float leftGutter = KumQuat.viewport.getLeftGutterWidth();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		KumQuat.camera.update();
		KumQuat.batch.setProjectionMatrix(KumQuat.camera.combined);
		KumQuat.batch.begin();
		
		/*byte up = 0;
		byte right = 0;
		byte down = 0;
		byte left = 0;*/
		
		
		for(int i = 0; i < GeneratorScreen.maze.tiles.length; i ++)
			for(int j = 0; j < GeneratorScreen.maze.tiles[0].length; j ++)
				if(i*32+scrollX>-350&&i*32+scrollX<1150&&j*32+scrollY>-32&&j*32+scrollY<600)
				{
					KumQuat.batch.draw(Resources.getImage("tiles/floor-1"), i*32 + scrollX, j*32 + scrollY);
					if(GeneratorScreen.maze.tiles[i][j] != 0){
						
						KumQuat.batch.draw(Resources.getImage("tiles/wall-"+(GeneratorScreen.maze.tiles[i][j]&1)+"-"+((GeneratorScreen.maze.tiles[i][j]&2) == 0 ? 0 : 1 )+"-"+((GeneratorScreen.maze.tiles[i][j]&4) == 0 ? 0 : 1)+"-"+((GeneratorScreen.maze.tiles[i][j]&8) == 0 ? 0 : 1)), i*32+scrollX, j*32+scrollY);
					}
				}
		
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
