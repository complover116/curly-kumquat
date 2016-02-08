package com.bitkeks.ckq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScreen implements Screen {

	float scrollX;
	float scrollY;
	
	float WIDTH;
	float HEIGHT;
	float Xtension;
		
	//AndroidButton buttons[] = new AndroidButton[4];
	public GameScreen() {}
	@Override
	public void render(float delta) {
		
		Input.processInput();
		//Input.processKeyInput();
		
		CurGame.character.tick(delta);
		
		//float leftGutter = KumQuat.viewport.getLeftGutterWidth();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		KumQuat.camera.position.x = CurGame.character.x;
		KumQuat.camera.position.y = CurGame.character.y;
		if(CurGame.character.x < WIDTH/2-200)
			KumQuat.camera.position.x = WIDTH/2-200;
		if(CurGame.character.x > CurGame.maze.tiles[0].length*32 - WIDTH/2)
			KumQuat.camera.position.x = CurGame.maze.tiles[0].length*32 - WIDTH/2;
		if(CurGame.character.y < HEIGHT/2)
			KumQuat.camera.position.y = HEIGHT/2;
		if(CurGame.character.y > CurGame.maze.tiles[0][0].length*32 - HEIGHT/2)
			KumQuat.camera.position.y = CurGame.maze.tiles[0][0].length*32 - HEIGHT/2;
		
		KumQuat.camera.update();
		KumQuat.batch.setProjectionMatrix(KumQuat.camera.combined);
		KumQuat.cache.setProjectionMatrix(KumQuat.camera.combined);
		KumQuat.shapeRenderer.setProjectionMatrix(KumQuat.UIcamera.combined);
		KumQuat.cache.begin();
		
		/*byte up = 0;
		byte right = 0;
		byte down = 0;
		byte left = 0;*/
		KumQuat.cache.draw(CurGame.maze.caheIDs[CurGame.curLayer]);
		
		KumQuat.cache.end();
		KumQuat.batch.begin();
		
		KumQuat.batch.draw(Resources.getImage("character/idle"), CurGame.character.x, CurGame.character.y, 15.5f, 15.5f, 32, 32, 1,1, -CurGame.character.rot);
		KumQuat.batch.end();
		
		Gdx.gl.glEnable(GL20.GL_BLEND);
		KumQuat.shapeRenderer.begin(ShapeType.Filled);
		KumQuat.shapeRenderer.setColor(1, 1, 1, 0.5f);
		KumQuat.shapeRenderer.circle(100, 100, 100);
		
		KumQuat.shapeRenderer.setColor(1, 1, 1, 1);
		KumQuat.shapeRenderer.circle(100+Input.joyX, 100+Input.joyY, 40);
		
		KumQuat.shapeRenderer.end();
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
		KumQuat.UIviewport.update(width, height);
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
